package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.entity.DgiRepair;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.demo.repository.DgiRepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public void create(DgiRepair dgiRepair){
        DgiInfo device = dgiInfoRepository.findById(dgiRepair.getDgiId()).orElseThrow(IllegalArgumentException::new);
        if(5==device.getStatus()){              //数采仪的状态为维修中
            if(1==dgiRepair.getResult()){       //维修结果为维修成功
                device.setStatus(6);            //设置数采仪状态为维修成功
            }else if(2==dgiRepair.getResult()){ //维修结果为维修失败
                device.setStatus(8);            //设置数采仪状态为已报废
            }
            dgiRepairRepository.save(dgiRepair);
        }else{
            throw new IllegalArgumentException("此数采仪的状态不为维修中 无法修改状态");
        }
    }

}
