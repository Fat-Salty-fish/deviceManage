package com.sinoyd.demo.config;

import com.sinoyd.frame.base.configuration.PrincipalContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-04 11:48
 */

@Component(value = "auditorAware")
public class AuditorAwareImpl implements AuditorAware<String>{
    @Value(value = "${user.name}")
    private String userName;
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(userName);
    }
}
