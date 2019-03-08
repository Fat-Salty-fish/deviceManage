package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.DgiSaleDetail;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiSaleDetailService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 17:12
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device/sale/detail")
public class DgiSaleDetailController extends BaseController {
    @Autowired
    private DgiSaleDetailService dgiSaleDetailService;

    /**
     * 新增一条销售详细信息
     * @param dgiSaleDetail 销售详细信息
     * @return  返回状态码、消息 不包括传入的销售消息信息 状态码为0表示添加成功
     */
    @PostMapping("")
    public Object create(DgiSaleDetail dgiSaleDetail){
        dgiSaleDetailService.create(dgiSaleDetail);
        return ResultBean.success();
    }
}
