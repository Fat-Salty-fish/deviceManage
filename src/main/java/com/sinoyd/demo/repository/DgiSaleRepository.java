package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.DgiSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:42
 */
public interface DgiSaleRepository extends JpaRepository<DgiSale,Integer> {
    List<DgiSale> findAllByPsId(Integer psId);
    DgiSale findBySaleContract(String saleContract);
}
