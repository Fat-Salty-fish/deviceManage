package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.entity.DgiTest;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.demo.repository.DgiTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:38
 */
@Service
public class DgiTestService {
    @Autowired
    private DgiTestRepository dgiTestRepository;

    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    //只能对状态为测试中的数采仪进行测试并保存结果
    //只能设置数采仪的状态为测试通过与测试不通过
    @Transactional
    public void create(DgiTest dgiTest){
        DgiInfo device = dgiInfoRepository.findById(dgiTest.getDgiId()).orElseThrow(IllegalArgumentException::new);
        if(2==device.getStatus()){              //如果数采仪的状态为测试中
            if(1==dgiTest.getResult()){         //如果测试结果为测试通过
                device.setStatus(4);            //设置数采仪的状态为测试通过
            }else if(2==dgiTest.getResult()){   //如果测试结果为不通过
                device.setStatus(3);            //设置数采仪的状态为测试不通过
            }
            dgiTestRepository.save(dgiTest);    //保存测试记录
        }else {                                 //数采仪状态不是测试中 通过此接口无法修改数采仪状态
            throw new IllegalArgumentException("此数采仪状态不为测试中 无法修改状态");
        }
    }
}
