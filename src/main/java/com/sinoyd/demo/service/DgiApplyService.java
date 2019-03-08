package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiApply;
import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.repository.DgiApplyRepository;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.frame.base.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:56
 */
@Service
public class DgiApplyService  {
    @Autowired
    private DgiApplyRepository dgiApplyRepository;

    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    //工作场景：在数采仪新增之后 数采仪维修、测试、销售等行为之前 需要先对数采仪领用
    //领用记录里需要记录领用目的
    //流程：在领用记录表中新增领用记录 并修改对应数采仪的状态
    @Transactional
    public void create(DgiApply apply){
        DgiInfo device = dgiInfoRepository.findById(apply.getDgiId()).orElseThrow(IllegalArgumentException::new);
        if(1==device.getStatus()){
            if(1==apply.getApplyPurpose()){ //如果领用目的为测试
                device.setStatus(2);        //设置设备状态为测试中
            }else if (2==apply.getApplyPurpose()){//如果领用目的为维修
                device.setStatus(5);        //设置设备状态为维修中
            }
            dgiApplyRepository.save(apply); //保存领用
        }else {
            throw new IllegalArgumentException("此数采仪状态不为已入库 无法修改状态");
        }
    }

    public Page<DgiApply> findAllApplyInfo(Integer id) {
        return null;
    }
}
