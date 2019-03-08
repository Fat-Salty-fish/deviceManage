package com.sinoyd.demo.controller;

import com.sinoyd.demo.criteria.ProductBatchCriteria;
import com.sinoyd.demo.entity.ProductBatch;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.ProductBatchService;
import com.sinoyd.frame.base.controller.BaseController;
import com.sinoyd.frame.base.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
     * @return  返回状态码、消息 不返回传入的批号对象 状态码为0表示新增成功
     */
    @PostMapping("")
    public Object create(@RequestBody ProductBatch productBatch){
        productBatchService.create(productBatch);
        return ResultBean.success();
    }

    /**
     * 根据批号id查找一条数采仪批号信息
     * @param batchId   数采仪批号id
     * @return  返回状态码、消息以及单条数采仪批号信息
     */
    @GetMapping("/{id}")
    public Object findById(@PathVariable("id")Integer batchId){
        return ResultBean.success(productBatchService.findById(batchId));
    }

    /**
     *  分页搜索多条数采仪批号信息
     * @param criteria  搜索条件 包括数采仪名称 批号名称 所属公司id 状态
     * @return 返回状态码、消息、总数、页数以及多条数采仪批号信息
     */
    @GetMapping("")
    public Object findByPage(ProductBatchCriteria criteria){
        PageBean pageBean = super.getPageBean();
        productBatchService.findByPage(pageBean,criteria);
        return ResultBean.success(pageBean);
    }

    /**
     * 更新一条数采仪批号信息
     * @param productBatch  要更新的数采仪批号信息
     * @return  返回状态码、消息 不包括传入的数采仪批号信息 状态码为0表示更新成功
     */
    @PutMapping("")
    public Object update(@RequestBody ProductBatch productBatch){
        productBatchService.update(productBatch);
        return ResultBean.success();
    }

    /**
     * 根据id删除多条数采仪批号信息
     * @param productBatchIds   要删除的数采仪批号id集合
     * @return  返回状态码、消息以及删除的数采仪批号条数
     */
    @DeleteMapping("")
    public Object delete(@RequestBody Collection<Integer> productBatchIds){
        return ResultBean.success(productBatchService.delete(productBatchIds));
    }


}
