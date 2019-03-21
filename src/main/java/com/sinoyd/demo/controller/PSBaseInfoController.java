package com.sinoyd.demo.controller;

import com.sinoyd.demo.criteria.PSBaseInfoCriteria;
import com.sinoyd.demo.resultBean.ResultBean;
import com.sinoyd.demo.entity.PSBaseInfo;
import com.sinoyd.demo.service.PSBaseInfoService;
import com.sinoyd.frame.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Description 企业基础信息controller层
 * @auther 李忠杰
 * @create 2019-02-27 13:57
 */
@RestController
@RequestMapping("/api/bas/deviceManagement/company")
public class PSBaseInfoController extends BaseController {
    @Autowired
    private PSBaseInfoService psBaseInfoService;

    /**
     * 创建一个新的企业信息
     *
     * @param companyInfo 传入的企业基础信息
     * @return 返回状态码以及消息 不返回传入的企业基础信息 状态码为0表示新增成功
     */
    @PostMapping("")
    public Object create(@RequestBody PSBaseInfo companyInfo) {
        psBaseInfoService.create(companyInfo);
        return ResultBean.success();
    }

    /**
     * 根据企业id获取企业信息
     *
     * @param companyInfoId 传入的企业id
     * @return 返回状态码、消息以及单个企业的信息
     */
    @GetMapping("/{id}")
    public Object findById(@PathVariable("id") Integer companyInfoId) {
        return ResultBean.success(psBaseInfoService.findById(companyInfoId));
    }

    /**
     * 根据搜索条件进行模糊搜索以及分页搜索
     *
     * @param criteria 搜索条件 企业名称以及企业类型
     * @return 返回状态码、消息、总数、当前页数以及多条企业信息
     */
    @GetMapping("")
    public Object findByPage(PSBaseInfoCriteria criteria) {
        return ResultBean.success(psBaseInfoService.findByPage(criteria));
    }

    /**
     * 更新企业信息
     *
     * @param companyInfo 传入的企业信息 包括企业id 若没有id则会进行报错
     * @return 返回状态码、消息 不返回传入的企业信息 状态码为0表示更新成功
     */
    @PutMapping("")
    public Object update(@RequestBody PSBaseInfo companyInfo) {
        psBaseInfoService.update(companyInfo);
        return ResultBean.success();
    }

    /**
     * 删除企业信息
     *
     * @param companyInfoIds 以集合的形式传入要删除的企业信息id
     * @return 返回状态码、消息以及删除的信息的条数
     */
    @DeleteMapping("")
    public Object delete(@RequestBody Collection<Integer> companyInfoIds) {
        return ResultBean.success(psBaseInfoService.delete(companyInfoIds));
    }

}
