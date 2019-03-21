package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.entity.DgiTest;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiInfoService;
import com.sinoyd.demo.service.DgiTestService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:36
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device")
public class DgiTestController extends BaseController {
    @Autowired
    private DgiTestService dgiTestService;

    /**
     * 创建一条数采仪测试信息
     * @param dgiTest   数采仪测试信息
     * @return  返回状态码、消息 数采仪销售信息
     */
    @PostMapping("/test")
    public Object create(@RequestBody DgiTest dgiTest){
        return ResultBean.success(dgiTestService.create(dgiTest));
    }

    /**
     * 根据dgiId获取对应数采仪的全部测试信息
     * @param dgiId 数采仪id
     * @return  返回状态码 消息 以及全部的数采仪测试信息
     */
    @GetMapping("/{dgiId}/test")
    public Object findAll(@PathVariable("dgiId")Integer dgiId){
        return ResultBean.success(dgiTestService.findAllTestInfo(dgiId));
    }

    /**
     * 根据testId删除数采仪测试信息 并且将数采仪状态返回到之前的状态
     * @param testId   数采仪测试信息id
     * @return  返回状态码 消息 状态码为0为删除成功
     */
    @DeleteMapping("/test")
    public Object delete(@RequestParam("testId")Integer testId){
        dgiTestService.delete(testId);
        return ResultBean.success();
    }

    /**
     * 根据testId查找某个测试详细信息
     * @param testId    测试信息id
     * @return  返回状态码 消息 以及测试详细信息
     */
    @GetMapping("**/test/{testId}")
    public Object findOne(@PathVariable("testId")Integer testId){
        return ResultBean.success(dgiTestService.findOne(testId));
    }
}
