package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.DgiSale;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.demo.repository.DgiSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public DgiSale create(DgiSale dgiSale){
        return dgiSaleRepository.save(dgiSale);
    }

    public DgiSale findById(Integer id){
        return dgiSaleRepository.findById(id).orElse(null);
    }
}
