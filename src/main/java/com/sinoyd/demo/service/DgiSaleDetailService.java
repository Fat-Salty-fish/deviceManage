package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.entity.DgiSale;
import com.sinoyd.demo.entity.DgiSaleDetail;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.demo.repository.DgiSaleDetailRepository;
import com.sinoyd.demo.repository.DgiSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:57
 */
@Service
public class DgiSaleDetailService {
    @Autowired
    private DgiSaleDetailRepository dgiSaleDetailRepository;

    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    @Autowired
    private DgiSaleRepository dgiSaleRepository;

    //添加一条新的销售详细记录
    //流程：获取数采仪信息 以及合同信息 因为需要修改数采仪信息以及合同信息
    //判断合同的需求数量是否已经满足 如未满足则可以继续添加 如果已满足则不可以继续添加
    //继续添加时先将数采仪设备修改 将合同内已出库的数量加一 再保存一条销售详细记录
    @Transactional
    public void create(DgiSaleDetail dgiSaleDetail) {
        //获取获取数采仪信息
        DgiInfo device = dgiInfoRepository.findById(dgiSaleDetail.getDgiId()).orElseThrow(IllegalArgumentException::new);
        //获取销售合同信息
        DgiSale sale = dgiSaleRepository.findById(dgiSaleDetail.getSaleId()).orElseThrow(IllegalArgumentException::new);
        if (sale.getDifference()>0) {
            if (4 == device.getStatus()) {          //数采仪的状态为测试通过
                device.setStatus(7);            //设置数采仪的状态为已出库
                Integer outAmount = sale.getOutAmount();
                outAmount +=1;
                sale.setOutAmount(outAmount);
                dgiSaleDetailRepository.save(dgiSaleDetail);    //保存出库信息
            } else {
                throw new IllegalArgumentException("此数采仪的状态不为测试通过 无法修改状态");
            }
        }else {
            throw new IllegalArgumentException("此合同已经达到需求 无法继续出库");
        }
    }
}
