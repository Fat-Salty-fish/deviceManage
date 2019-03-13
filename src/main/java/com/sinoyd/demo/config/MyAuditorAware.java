package com.sinoyd.demo.config;

import com.sinoyd.demo.entity.UserInfo;
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
public class MyAuditorAware implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Optional<String> result = new Optional<>(((UserInfo) authentication.getPrincipal()).getName());
        return result;
    }
}
