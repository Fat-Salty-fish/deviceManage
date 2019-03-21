package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiApply;
import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.repository.DgiApplyRepository;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.frame.base.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:56
 */
@Service
public class DgiApplyService {
    @Autowired
    private DgiApplyRepository dgiApplyRepository;

    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    //工作场景：在数采仪新增之后 数采仪维修、测试、销售等行为之前 需要先对数采仪领用
    //领用记录里需要记录领用目的
    //流程：在领用记录表中新增领用记录 并修改对应数采仪的状态
    @Transactional
    public DgiApply create(DgiApply apply) {
        DgiInfo device = dgiInfoRepository.findById(apply.getDgiId()).orElseThrow(IllegalArgumentException::new);
        if (1 == device.getStatus()) {
            if (1 == apply.getApplyPurpose()) { //如果领用目的为测试
                device.setStatus(2);        //设置设备状态为测试中
            } else if (2 == apply.getApplyPurpose()) {//如果领用目的为维修
                device.setStatus(5);        //设置设备状态为维修中
            }
            return dgiApplyRepository.save(apply); //保存领用
        } else {
            throw new IllegalArgumentException("此数采仪状态不为已入库 无法修改状态");
        }
    }

    //根绝applyId删除某个领用信息 并将数采仪状态修改为领用之前的状态
    @Transactional
    public void delete(Integer applyId) {
        DgiApply dgiApply = dgiApplyRepository.getOne(applyId);
        if (dgiApply == null) {
            return;
        }
        DgiInfo dgiInfo = dgiInfoRepository.getOne(dgiApply.getDgiId());
        dgiInfo.setStatus(dgiApply.getStatusBefore());
        dgiApplyRepository.delete(dgiApply);
    }

    //获取某个消耗品的全部领用信息
    public List<DgiApply> findAllApplyInfo(Integer dgiId) {
        return dgiApplyRepository.findAllByDgiId(dgiId);
    }

    public DgiApply findOne(Integer applyId){
        return dgiApplyRepository.getOne(applyId);
    }
}
