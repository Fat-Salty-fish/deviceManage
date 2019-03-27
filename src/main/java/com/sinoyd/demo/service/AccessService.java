package com.sinoyd.demo.service;

import com.sinoyd.demo.dingding.DingDingTools;
import com.sinoyd.demo.entity.UserInfo;
import com.sinoyd.demo.entity.UserInfoSimplify;
import com.sinoyd.demo.token.MyToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Description 用于处理免登陆操作的service
 * @auther 李忠杰
 * @create 2019-03-11 14:19
 */
@Service
public class AccessService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MyToken myToken;

    Logger logger = LoggerFactory.getLogger(getClass());

    //获取access_token以及使用access_token获取员工信息
    //获取员工信息之后 生成token 返回到前端 并将此token存入redis中
    //之后只需要判断传入的信息中是否有token 有token则验证token是否已经失效
    //若无Token则向钉钉发起请求 请求用户信息
    //此函数为用户首次登陆时 向钉钉发起请求的情况
    public UserInfo findUser(String code, HttpServletResponse response) {
        if (StringUtils.isBlank(code)) {
            throw new NullPointerException("传入的code无效 无法访问 请传入有效的code值");
        }
        logger.info("Code:" + code);
        String accessToken = DingDingTools.getAccessToken();            //获取access_token
        logger.info("AccessToken:" + accessToken);
        String userId = DingDingTools.getUserId(code, accessToken);     //获取userId
        logger.info("UserId:" + userId);
        UserInfo user = DingDingTools.getUserInfo(userId, accessToken);  //获取userInfo
        UserInfoSimplify simplify = new UserInfoSimplify(user);
        //生成或获取token
        //判断redis中是否已经存在了这个用户的token 如果存在 则延长时间 如果不存在则生成新的token

        String nameKey = "User:" + simplify.getName() + ":Token";
        String userToken = null;
        userToken = (String) redisTemplate.opsForValue().get(nameKey);
        if (StringUtils.isBlank(userToken)) {
            logger.info("redis中无此用户token 生成新的token");
            userToken = myToken.createJWT(simplify);                 //生成token
        } else {
            logger.info("redis中已经存在此用户token 不生成新的token");
            logger.info(userToken);
        }
        //将token延时或定义存在时间
        redisTemplate.opsForValue().set(nameKey, userToken, 2 * 60 * 60, TimeUnit.SECONDS);
        logger.info("用户" + simplify.getName() + "此次登录的token为:" + userToken);
        response.setHeader("token", userToken);
        return user;
    }
}
