package com.sinoyd.demo.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sinoyd.demo.entity.DgiApply;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiApplyService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:58
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device/apply")
public class DgiApplyController extends BaseController {
    @Autowired
    private DgiApplyService dgiApplyService;

    /**
     * 新增数采仪领用信息
     * @param dgiApply  数采仪领用信息
     * @return  返回状态码、消息 不包括数采仪领用信息 状态码值为0表示新增成功
     */
    @PostMapping("")
    private Object create(@RequestBody DgiApply dgiApply){
        dgiApplyService.create(dgiApply);
        return ResultBean.success();
    }

    /**
     * 根据id获取某个数采仪的申领信息
     * @param id    数采仪id
     * @return      返回状态码、消息、以及数采仪申领信息
     */
    @GetMapping("/{id}")
    private Object findByPage(@PathVariable("id")Integer id){
        return ResultBean.success(dgiApplyService.findAllApplyInfo(id));
    }
}
