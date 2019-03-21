package com.sinoyd.demo.controller;

import com.sinoyd.demo.resultBean.ResultBean;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 异常处理controller层 截获异常并进行相应
 * @auther 李忠杰
 * @create 2019-02-27 14:46
 */
@RestControllerAdvice

public class ExceptionHandlerController {

    /**
     * 截获空指针异常 并作出相应反应
     * @param e
     * @return  对截获的异常进行处理统一返回
     */
    @ExceptionHandler
    @ResponseBody
    public Object nullPointerExceptionHandler(NullPointerException e){
        return ResultBean.error(1,e);
    }

    @ExceptionHandler
    @ResponseBody
    public Object MalformedJwtExceptionHandler(MalformedJwtException e){
        return ResultBean.error(1, "token解析错误 请先登录获取token");
    }

    @ExceptionHandler
    @ResponseBody
    public Object IllegalArgumentHandler(IllegalArgumentException e){
        return ResultBean.error(2,e);
    }

    /**
     * 截获其他异常 并作出相应反应
     * @param e 处以上异常外的其他异常
     * @return  对截获的异常进行处理统一返回
     */
//    @ExceptionHandler
//    @ResponseBody
//    public Object ExceptionHandler(Exception e){
//        return ResultBean.error(2,e);
//    }
}
