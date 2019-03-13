package com.sinoyd.demo.security;

import com.sinoyd.demo.entity.UserInfo;
import com.sinoyd.demo.token.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-13 15:27
 */
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private RedisTemplate redisTemplate;

    private final Map<String, SimpleGrantedAuthority> authorityMap = new ConcurrentHashMap<>();


    //在这个方法里对token进行验证
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) authentication;
        //获取token
        String token = authenticationToken.getToken();
        //如果token为空 则用户需要先进行登录 登录应该调用/api/bas/deviceManagement/login接口
        //通过上面的接口获取token 再进行验证
        //当token不为空时 因为是Token 所以将token先解析 解析之后获取用户信息 根据用户信息获取redis里的token
        //与传入的token进行匹配 如果匹配则登录成功并赋予权限 如果匹配不成功则登录不成功
        if(token == null){
            return null;
        }else {
            UserInfo userInfo = (UserInfo)MyToken.parseJWT(token).get("userInfo");  //从token中解析用户信息
            String userId = userInfo.getUserid();                                   //从token中解析出用户的id
            String tokenToVerify = (String)redisTemplate.opsForValue().get("userId:"+userId);
            //获取到的token与存在redis中的token相同
            if(tokenToVerify!=null && tokenToVerify==token){
                return new AuthenticationToken(userInfo.getUserid(),userInfo.getName(),userInfo.getPosition(),null);
            }
            else{
                return null;
            }
        }
    }

    //只支持AuthenticationToken这个类来验证身份
    @Override
    public boolean supports(Class<?> authentication) {
        return (AuthenticationToken.class.isAssignableFrom(authentication));
    }
}
