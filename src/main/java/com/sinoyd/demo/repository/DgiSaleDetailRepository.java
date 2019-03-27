package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.DgiSaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:56
 */
public interface DgiSaleDetailRepository extends JpaRepository<DgiSaleDetail,Integer> {
    DgiSaleDetail findByDgiId(Integer dgiId);
}
