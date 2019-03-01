package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.ProductBatch;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.ProductBatchService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 数采仪商品批号以及设备管理controller层
 * @auther 李忠杰
 * @create 2019-03-01 16:25
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/batch")
public class ProductBatchController extends BaseController {
    @Autowired
    private ProductBatchService productBatchService;

    /**
     * 创建一个新的数采仪批号
     * @param productBatch  数采仪批号对象
     * @return  返回结构化信息 不包括保存的对象信息 默认保存成功
     */
    @PostMapping("")
    public Object create(@RequestBody ProductBatch productBatch){
        productBatchService.create(productBatch);
        return ResultBean.success();
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable("id")Integer batchId){
        return ResultBean.success(productBatchService.findById(batchId));
    }

    @GetMapping("")
    public Object findByPage()
}
