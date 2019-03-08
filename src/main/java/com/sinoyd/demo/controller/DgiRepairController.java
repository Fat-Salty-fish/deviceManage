package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.DgiRepair;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiRepairService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:20
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device/repair")
public class DgiRepairController extends BaseController {
    @Autowired
    private DgiRepairService dgiRepairService;

    /**
     * 创建一条数采仪维修信息
     * @param dgiRepair 数采仪维修信息
     * @return  返回状态码、消息 不包括传入的数采仪维修信息 状态码为0表示新增成功
     */
    @PostMapping("")
    public Object create(@RequestBody DgiRepair dgiRepair){
        dgiRepairService.create(dgiRepair);
        return ResultBean.success();
    }
}
