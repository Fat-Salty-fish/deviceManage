package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.ProductBatch;
import com.sinoyd.demo.repository.ProductBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-01 16:27
 */
@Service
public class ProductBatchService {
    @Autowired
    private ProductBatchRepository productBatchRepository;

    public void create(ProductBatch productBatch){
        productBatchRepository.save(productBatch);
    }

    public ProductBatch findById(Integer batchId){
        return productBatchRepository.findOne(batchId);
    }
}
