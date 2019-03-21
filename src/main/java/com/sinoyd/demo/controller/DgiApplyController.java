package com.sinoyd.demo.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sinoyd.demo.entity.DgiApply;
import com.sinoyd.demo.entity.DgiInfo;
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
@RequestMapping("/api/bas/deviceManagement/device")
public class DgiApplyController extends BaseController {
    @Autowired
    private DgiApplyService dgiApplyService;
    /**
     * 新增数采仪领用信息
     *
     * @param
     * @return 返回数采仪领用
     */
    @PostMapping("/apply")
    public Object create(@RequestBody DgiApply dgiApply) {
        return ResultBean.success(dgiApplyService.create(dgiApply));
    }

    /**
     * 根据id获取某个数采仪的申领信息
     * @param dgiId 数采仪id
     * @return 返回状态码、消息、以及数采仪申领信息
     */
    @GetMapping("/{dgiId}/apply")
    public Object findAll(@PathVariable("dgiId") Integer dgiId) {
        return ResultBean.success(dgiApplyService.findAllApplyInfo(dgiId));
    }

    /**
     * 根据applyId删除某个领用信息并将数采仪状态修改为之前的状态
     * @param applyId
     * @return  返回状态码、消息 状态码为0认为删除成功
     */
    @DeleteMapping("/apply")
    public Object delete(@RequestParam("applyId")Integer applyId){
        dgiApplyService.delete(applyId);
        return ResultBean.success();
    }

    /**
     * 根据applyId获取某个领用信息
     * @param applyId   领用信息id
     * @return  返回状态码、消息、申领信息
     */
    @GetMapping("**/apply/{applyId}")
    public Object findOne(@PathVariable("applyId")Integer applyId){
        return ResultBean.success(dgiApplyService.findOne(applyId));
    }
}
