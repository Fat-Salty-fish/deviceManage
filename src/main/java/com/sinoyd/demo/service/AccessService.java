package com.sinoyd.demo.service;

import com.sinoyd.demo.dingding.DingDingTools;
import com.sinoyd.demo.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用于处理免登陆操作的service
 * @auther 李忠杰
 * @create 2019-03-11 14:19
 */
@Service
public class AccessService {
    public UserInfo findUser(String code, HttpServletResponse response) {
        String accessToken = DingDingTools.getAccessToken();
        String userId = DingDingTools.getUserId(code, accessToken);
        UserInfo user = DingDingTools.getUserInfo(userId,accessToken);
        response.setHeader("access_token", accessToken);
        return user;
    }
}
