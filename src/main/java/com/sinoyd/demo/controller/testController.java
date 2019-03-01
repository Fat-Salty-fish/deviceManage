package com.sinoyd.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-02-26 9:44
 */
@RestController
public class testController {

    @GetMapping("/")
    public String index(HttpServletRequest request){
        System.out.println(request.getHeaderNames());
        System.out.println(request.getQueryString());
        System.out.println(request.getCookies());
        return "Hello World!";
    }
}
