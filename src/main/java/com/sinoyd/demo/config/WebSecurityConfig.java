package com.sinoyd.demo.config;

import com.sinoyd.frame.base.entity.CurrentPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-12 16:02
 */
@Configuration
public class WebSecurityConfig extends AbstractSecurityWebApplicationInitializer {

}
