package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.DgiApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-05 14:55
 */
public interface DgiApplyRepository extends JpaRepository<DgiApply,Integer> {
    List<DgiApply> findAllByDgiId(Integer dgiId);
}
