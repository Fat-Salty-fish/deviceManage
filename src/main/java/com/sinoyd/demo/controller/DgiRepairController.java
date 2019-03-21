package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.DgiRepair;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiRepairService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:20
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device")
public class DgiRepairController extends BaseController {
    @Autowired
    private DgiRepairService dgiRepairService;

    /**
     * 创建一条数采仪维修信息
     * @param dgiRepair 数采仪维修信息
     * @return  返回状态码、消息 数采仪维修信息
     */
    @PostMapping("/repair")
    public Object create(@RequestBody DgiRepair dgiRepair){
        return ResultBean.success(dgiRepairService.create(dgiRepair));
    }

    /**
     * 获取某个数采仪的全部维修信息
     * @param dgiId 数采仪id
     * @return  返回状态码、消息以及某个数采仪的全部维修信息
     */
    @GetMapping("/{dgiId}/repair")
    public Object findAll(@PathVariable("dgiId")Integer dgiId){
        return ResultBean.success(dgiRepairService.findAll(dgiId));
    }

    /**
     * 根据维修id删除某个维修信息 并将数采仪状态修改为之前的状态
     * @param repairId 维修信息id
     * @return  返回状态码 消息 状态码为0表示删除成功
     */
    @DeleteMapping("/repair")
    public Object delete(@RequestParam("repairId")Integer repairId){
        dgiRepairService.delete(repairId);
        return ResultBean.success();
    }

    /**
     * 根据维修id查找某个维修详细信息
     * @param repairId 维修信息id
     * @return  返回状态码 消息 以及维修详细信息
     */
    @GetMapping("**/repair/{repairId}")
    public Object findOne(@PathVariable("repairId")Integer repairId){
        return ResultBean.success(dgiRepairService.findOne(repairId));
    }

}
