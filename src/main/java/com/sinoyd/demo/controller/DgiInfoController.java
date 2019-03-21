package com.sinoyd.demo.controller;

import com.sinoyd.demo.criteria.DgiInfoCriteria;
import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.service.DgiInfoService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Description 数采仪详细信息controller层 包括数采仪的新增 删除 修改等功能
 * @auther 李忠杰
 * @create 2019-03-04 10:14
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/device")
public class DgiInfoController extends BaseController {
    @Autowired
    private DgiInfoService dgiInfoService;

    /**
     * 新增一台数采仪
     * @param dgiInfo   一台数采仪的详细信息
     * @return  返回状态码、消息 不包括数采仪详细信息 状态码为0表示新增成功
     */
    @PostMapping("")
    public Object create(@RequestBody DgiInfo dgiInfo){
        return ResultBean.success(dgiInfoService.create(dgiInfo));
    }

    /**
     * 根据某个数采仪的编号或者id获取数采仪详细信息  当输入的数字为11位时 认为传入的是编号 否则认为输入的数字为id
     * @param idOrCode    数采仪编号或者id
     * @return  返回状态码、消息以及单条数采仪详细信息
     */
    @GetMapping("/{idOrCode}")
    public Object findByCode(@PathVariable("idOrCode")String idOrCode){
        return ResultBean.success(dgiInfoService.findByCode(idOrCode));
    }


    /**
     * 获取数采仪分组信息并分页
     * @param criteria
     * @return
     */
    @GetMapping("/group")
    public Object findByGroup(DgiInfoCriteria criteria){
        return ResultBean.success(dgiInfoService.findByGroup(criteria));
    }


    /**
     * 分页获取数采仪详细信息
     * @param dgiInfoCriteria   搜索条件
     * @return  返回状态码、消息、总数、页数以及多条数采仪信息
     */
    @GetMapping("")
    public Object findDetailByPage(DgiInfoCriteria dgiInfoCriteria){
        return ResultBean.success(dgiInfoService.findDetailByPage(dgiInfoCriteria));
    }

    /**
     *  修改数采仪详细信息
     * @param dgiInfo   数采仪信息
     * @return  返回状态码、消息 不包括传入的数采仪详细信息 状态码为0表示更新成功
     */
    @PutMapping("")
    public Object update(@RequestBody DgiInfo dgiInfo){
        dgiInfoService.update(dgiInfo);
        return ResultBean.success();
    }

    /**
     * 根据id删除数采仪信息
     * @param dgiId  要删除的数采仪id
     * @return  返回状态码、消息以及删除的数采仪信息的条数
     */
    @DeleteMapping("")
    public Object delete(@RequestParam("dgiId")Integer dgiId){
        dgiInfoService.delete(dgiId);
        return ResultBean.success();
    }
}
