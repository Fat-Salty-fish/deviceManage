package com.sinoyd.demo.service;

import com.sinoyd.demo.entity.PSBaseInfo;
import com.sinoyd.demo.entity.ProductBatch;
import com.sinoyd.demo.repository.PSBaseInfoRepository;
import com.sinoyd.demo.repository.ProductBatchRepository;
import com.sinoyd.frame.base.repository.CommonRepository;
import com.sinoyd.frame.base.util.BaseCriteria;
import com.sinoyd.frame.base.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-01 16:27
 */
@Service
public class ProductBatchService {
    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private ProductBatchRepository productBatchRepository;

    @Autowired
    private PSBaseInfoRepository psBaseInfoRepository;

    public void create(ProductBatch productBatch) {
        productBatchRepository.save(productBatch);
    }

    public ProductBatch findById(Integer batchId) {
        ProductBatch productBatch = productBatchRepository.findById(batchId).orElse(null);
        Integer psId = productBatch.getPsId();
        PSBaseInfo psBaseInfo = psBaseInfoRepository.findById(psId).orElse(null);
        productBatch.setPsBaseInfo(psBaseInfo);
        return productBatch;
    }

    public void findByPage(PageBean pageBean, BaseCriteria productBatchCriteria) {
        pageBean.setEntityName(" ProductBatch a ");
        pageBean.setSelect(" Select a ");
        commonRepository.findByPage(pageBean, productBatchCriteria);
        List<ProductBatch> productBatches = pageBean.getData();
        List<Integer> psIds = productBatches.stream().map(ProductBatch::getPsId).collect(Collectors.toList());
        List<PSBaseInfo> psBaseInfos = psBaseInfoRepository.findAllByIdIn(psIds);
        productBatches.forEach(productBatch ->
                productBatch.setPsBaseInfo(psBaseInfos.stream().filter(psBaseInfo -> psBaseInfo.getId().equals(productBatch.getPsId())).findAny().orElse(null)));
    }

    @Transactional
    public void update(ProductBatch productBatch) {
        System.out.println("即将修改批号信息");
        if (productBatch.getId() == null) {
            throw new NullPointerException("批号id为空 无法更新信息");
        }
        productBatchRepository.save(productBatch);
    }

    @Transactional
    public Integer delete(Collection productBatchIds) {
        return productBatchRepository.deleteAllByIdIn(productBatchIds);
    }

}
