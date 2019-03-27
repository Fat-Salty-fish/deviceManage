package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiSale;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.demo.repository.DgiSaleRepository;
import com.sinoyd.frame.base.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:42
 */
@Service
public class DgiSaleService {
    @Autowired
    private DgiSaleRepository dgiSaleRepository;

    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    //新建一个销售合同
    public DgiSale create(DgiSale dgiSale){
        DgiSale sale = dgiSaleRepository.findBySaleContract(dgiSale.getSaleContract());
        if (sale!=null){
            return sale;
        }
        return dgiSaleRepository.save(dgiSale);
    }

    //根据销售合同的id获取销售合同的信息
    public DgiSale findById(Integer id){
        return dgiSaleRepository.getOne(id);
    }
}
