package com.sinoyd.demo.resultBean;

import com.sinoyd.frame.base.util.PageBean;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-02-27 14:06
 */
public class ResultBean {

    public static Map<String, Object> success() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "success");
        result.put("data",null);
        return result;
    }

    public static <T> Map<String, Object> success(T data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "success");
        result.put("data", data);
        return result;
    }

    public static  Map<String,Object> success(Page page){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msg","success");
        result.put("total",page.getTotalElements());
        result.put("page",page.getTotalPages());
        result.put("data",page.getContent());
        return result;
    }

    public static  Map<String, Object> success(PageBean data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "success");
        result.put("page",data.getPageNo());
        result.put("total",data.getRowsCount());
        result.put("data", data.getData());
        return result;
    }

    public static Map<String,Object> error(int code ,Exception e){
        Map<String,Object> result = new HashMap<>();
        result.put("code",code);
        result.put("msg",e.getMessage());
        result.put("data",null);
        return result;
    }

    public static Map<String,Object> error(int code,String message){
        Map<String,Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", message);
        result.put("data", null);
        return result;
    }

}
