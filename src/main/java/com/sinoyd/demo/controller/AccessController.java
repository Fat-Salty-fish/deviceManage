package com.sinoyd.demo.controller;

import com.sinoyd.demo.service.AccessService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-11 14:17
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/login")
public class AccessController extends BaseController {
    @Autowired
    private AccessService accessService;

    /**
     * 返回用户信息接口 传入用户免登码 然后先获取access_token 再通过access_token和免登码获取用户信息
     * @param code
     * @return
     */
    @GetMapping("")
    public Object login(@RequestParam("code")String code,HttpServletResponse response){
        return accessService.findUser(code,response);
    }
}
