package com.sinoyd.demo.config;

import com.sinoyd.demo.security.Detail;
import net.bytebuddy.pool.TypePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-13 9:33
 */
@Component("auditorAware")
public class MyAuditorAware implements AuditorAware<String> {

    Logger logger =  LoggerFactory.getLogger(getClass());

    @Override
    public Optional getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        try{
            Detail userDetail = (Detail) authentication.getPrincipal();
            String userName = userDetail.getUsername();
            Optional<String> result = Optional.ofNullable(userName);
            return result;
        }catch (Exception e){
            throw new IllegalArgumentException("Token验证错误 请重新登录");
        }
    }
}
