package com.sinoyd.demo.security;

import com.sinoyd.demo.security.AuthenticationProcessingFilter;
import com.sinoyd.demo.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-12 16:02
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    //token认证配置
    @Bean
    MyAuthenticationProvider myAuthenticationProvider(){
        return new MyAuthenticationProvider();
    }

    AuthenticationProcessingFilter authenticationProcessingFilter(AuthenticationManager authenticationManager){
        AuthenticationProcessingFilter authenticationProcessingFilter = new AuthenticationProcessingFilter();
        //为过滤器添加认证器
        authenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        //重写认证失败时的跳转页面
        authenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/api/bas/deviceManagement/login"));
        return authenticationProcessingFilter;
    }

    //配置登录端点
    @Bean
    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint(){
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint =
                new LoginUrlAuthenticationEntryPoint("/api/bas/deviceManagement/login");
        return loginUrlAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/","/api/bas/deviceManagement/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/")
                    .authenticationEntryPoint(loginUrlAuthenticationEntryPoint());
        http.addFilterBefore(authenticationProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.authenticationProvider(myAuthenticationProvider());
    }
}
