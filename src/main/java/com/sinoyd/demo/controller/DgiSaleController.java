package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.DgiSale;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiSaleService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:43
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device/sale")
public class DgiSaleController extends BaseController {
    @Autowired
    private DgiSaleService dgiSaleService;

    /**
     * 新增一条销售合同
     *
     * @param dgiSale 销售合同信息
     * @return 返回状态码、消息、同时返回合同信息 因为在下一个接口中需要此合同信息
     */
    @PostMapping("")
    public Object create(@RequestBody DgiSale dgiSale) {
        return ResultBean.success(dgiSaleService.create(dgiSale));
    }

    /**
     * 根据合同id获取一条合同信息
     *
     * @param id 合同id
     * @return
     */
    @GetMapping("/{id}")
    public Object findBySaleId(@PathVariable("id") Integer id) {
        return ResultBean.success(dgiSaleService.findById(id));
    }
}
