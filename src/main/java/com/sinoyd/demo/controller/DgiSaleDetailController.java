package com.sinoyd.demo.controller;

import com.sinoyd.demo.entity.DgiSaleDetail;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiSaleDetailService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 17:12
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device")
public class DgiSaleDetailController extends BaseController {
    @Autowired
    private DgiSaleDetailService dgiSaleDetailService;

    /**
     * 新增一条销售详细信息
     * @param dgiSaleDetail 销售详细信息
     * @return  返回状态码、消息 不包括传入的销售消息信息 状态码为0表示添加成功
     */
    @PostMapping("/sale/detail")
    public Object create(@RequestBody DgiSaleDetail dgiSaleDetail){
//        System.out.println(dgiSaleDetail);
        return ResultBean.success(dgiSaleDetailService.create(dgiSaleDetail));
    }

    /**
     *      * 根据销售详情id删除某个销售详情
     *      * @param saleDetailId  销售详情id
     *      * @return  返回状态码 消息 状态码为0默认删除成功
     */
    @DeleteMapping("**/sale/detail")
    public Object delete(@RequestParam("saleDetailId")Integer saleDetailId){
        dgiSaleDetailService.delete(saleDetailId);
        return ResultBean.success();
    }
}
