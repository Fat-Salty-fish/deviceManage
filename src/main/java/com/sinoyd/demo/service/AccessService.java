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
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private MyToken myToken;

    Logger logger =  LoggerFactory.getLogger(getClass());

    //获取access_token以及使用access_token获取员工信息
    //获取员工信息之后 生成token 返回到前端 并将此token存入redis中
    //之后只需要判断传入的信息中是否有token 有token则验证token是否已经失效
    //若无Token则向钉钉发起请求 请求用户信息
    //此函数为用户首次登陆时 向钉钉发起请求的情况
    public UserInfo findUser(String code, HttpServletResponse response) {
        if(StringUtils.isBlank(code)){
            throw new NullPointerException("传入的code无效 无法访问 请传入有效的code值");
        }
        logger.info("Code:"+code);
        String accessToken = DingDingTools.getAccessToken();            //获取access_token
        logger.info("AccessToken:"+accessToken);
        String userId = DingDingTools.getUserId(code, accessToken);     //获取userId
        logger.info("UserId:"+userId);
        UserInfo user = DingDingTools.getUserInfo(userId,accessToken);  //获取userInfo
        UserInfoSimplify simplify = new UserInfoSimplify(user);
        String userToken = myToken.createJWT(simplify);                 //生成token
        String nameKey = "User:"+simplify.getName()+":Token";

        redisTemplate.opsForValue().set(nameKey,userToken,2*60*60, TimeUnit.SECONDS);
        response.setHeader("token",userToken);
        return user;
    }
}
