package com.sinoyd.demo.security;

import com.sinoyd.demo.entity.UserInfoSimplify;
import com.sinoyd.demo.token.MyToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-20 9:03
 */

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    Logger logger = LoggerFactory.getLogger(getClass());

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.redisTemplate = redisTemplate;
    }

    //用来与redis进行交互

    private RedisTemplate<String, Object> redisTemplate;

    //用来存放权限
    static private Map<String, SimpleGrantedAuthority> authority = new HashMap();

    static {
        authority.put(null, new SimpleGrantedAuthority("NULL"));        //没有职位 没有对应的权限
        authority.put("Manager", new SimpleGrantedAuthority("ALL"));    //管理员 拥有全部权限
        authority.put("Tester", new SimpleGrantedAuthority("TESTER"));  //测试人员 拥有测试权限
        authority.put("Repairman", new SimpleGrantedAuthority("REPAIRMAN"));    //修理人员 拥有修理权限
        authority.put("Storekeeper", new SimpleGrantedAuthority("STOREKEEPER"));//仓库管理人员 拥有仓库设备管理权限 包括出库入库等
    }

    //用来解析token
    private MyToken myToken = new MyToken();

    //进行对请求的解析
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        //从请求头中获取token信息
        String headerToken = httpServletRequest.getHeader("token");
        //输出请求token
        logger.warn("请求Token为:" + headerToken);
        //获取所有头信息
        Enumeration<String> headerEnumeration = httpServletRequest.getHeaderNames();
        Map<String, String> headers = new HashMap<>();
        while (headerEnumeration.hasMoreElements()) {
            String key = headerEnumeration.nextElement();
            String value = httpServletRequest.getHeader(key);
            headers.put(key, value);
        }
        logger.warn("请求Headers为:" + headers.toString());
        //
        if (StringUtils.isNotBlank(headerToken) && !headerToken.equals("null") ) {
            logger.info("token信息不为空");
            //传入service中
            String userName = (String) myToken.parseJWT(headerToken).get("name");
            if (StringUtils.isNotBlank(headerToken) && StringUtils.isNotBlank(userName)) {
                //从redis中获取Token 并进行匹配
                String token = (String) redisTemplate.opsForValue().get("User:" + userName + ":Token");
                if (StringUtils.isNotBlank(token) && headerToken.equals(token)) {
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        //重新设置过期时间
                        redisTemplate.opsForValue().set("User:" + userName + ":Token", headerToken, 2 * 60 * 60, TimeUnit.SECONDS);
                        //从Token中获取用户信息 用来权限认证
                        UserInfoSimplify simplify = new UserInfoSimplify((Map) myToken.parseJWT(token).get("userInfo"));
                        UserDetails userDetails = new Detail(simplify.getName(), token, Arrays.asList(authority.get(simplify.getPosition())));
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }else {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                        logger.info("用户已经存在:"+((Detail)authentication.getPrincipal()).getUsername());
                    }
                } else {
                    logger.info("在redis中未找到对应的token 请求失败 请重新登录");
                    throw new BadCredentialsException("Token已经失效 请重新登录");
                }
            }
        }else {
            logger.info("token信息为空");
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null,null,null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
