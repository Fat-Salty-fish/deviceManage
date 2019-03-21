package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.DgiTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:38
 */
public interface DgiTestRepository extends JpaRepository<DgiTest,Integer> {
    List<DgiTest> findAllByDgiId(Integer dgiId);
}
