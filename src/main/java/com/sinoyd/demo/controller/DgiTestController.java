package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.entity.DgiTest;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiInfoService;
import com.sinoyd.demo.service.DgiTestService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:36
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device/test")
public class DgiTestController extends BaseController {
    @Autowired
    private DgiTestService dgiTestService;

    /**
     * 创建一条数采仪测试信息
     * @param dgiTest   数采仪测试信息
     * @return  返回状态码、消息 不包括传入的数采仪测试信息 状态码为0表示新增成功
     */
    @PostMapping("")
    public Object create(@RequestBody DgiTest dgiTest){
        dgiTestService.create(dgiTest);
        return ResultBean.success();
    }
}
