package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.DgiRepair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 16:18
 */
public interface DgiRepairRepository extends JpaRepository<DgiRepair,Integer> {
    List<DgiRepair> findAllByDgiId(Integer dgiId);
}
