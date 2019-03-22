package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.entity.DgiRepair;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.demo.repository.DgiRepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:18
 */
@Service
public class DgiRepairService {
    @Autowired
    private DgiRepairRepository dgiRepairRepository;

    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    //只能对维修中的数采仪进行维修
    //只能设置数采仪的状态为维修成功与报废
    @Transactional
    public DgiRepair create(DgiRepair dgiRepair) {
        DgiInfo device = dgiInfoRepository.findById(dgiRepair.getDgiId()).orElseThrow(IllegalArgumentException::new);
        if (5 == device.getStatus()) {              //数采仪的状态为维修中
            if (1 == dgiRepair.getResult()) {       //维修结果为维修成功
                device.setStatus(6);            //设置数采仪状态为维修成功
            } else if (2 == dgiRepair.getResult()) { //维修结果为维修失败
                device.setStatus(8);            //设置数采仪状态为已报废
            }
            return dgiRepairRepository.save(dgiRepair);
        } else {
            throw new IllegalArgumentException("此数采仪的状态不为维修中 无法修改状态");
        }
    }

    //根据消耗品id获取某个消耗品下的所有维修信息
    public List<DgiRepair> findAll(Integer dgiId) {
        return dgiRepairRepository.findAllByDgiId(dgiId);
    }

    //根据维修信息id删除对应的维修信息 并将数采仪状态修改为之前的状态
    @Transactional
    public void delete(Integer repairId) {
        DgiRepair dgiRepair = dgiRepairRepository.getOne(repairId);
        if (dgiRepair == null) {
            throw new NullPointerException("未能查询到id为" + repairId + "的维修信息");
        }
        DgiInfo dgiInfo = dgiInfoRepository.getOne(dgiRepair.getDgiId());
        dgiInfo.setStatus(dgiRepair.getStatusBefore());
        dgiRepairRepository.deleteById(repairId);
    }

    //根据维修信息id获取详细的维修信息
    public DgiRepair findOne(Integer repairId) {
        return dgiRepairRepository.getOne(repairId);
    }
}
