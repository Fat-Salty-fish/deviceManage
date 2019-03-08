package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.ProductBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-01 16:27
 */
public interface ProductBatchRepository extends JpaRepository<ProductBatch,Integer> {
    Integer deleteAllByIdIn(Collection productBatchIds);
}
