package com.sinoyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(value = "com.sinoyd.demo",useDefaultFilters = true,excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                value = {com.sinoyd.frame.base.configuration.PrincipalContext.class})})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
