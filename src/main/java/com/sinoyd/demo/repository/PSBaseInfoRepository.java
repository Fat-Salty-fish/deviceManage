package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.PSBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-02-27 13:55
 */
@Repository
public interface PSBaseInfoRepository extends PagingAndSortingRepository<PSBaseInfo,Integer>, JpaSpecificationExecutor<PSBaseInfo> {
    List<PSBaseInfo> findAllByIdIn(Collection<Integer> ids);
    Page<PSBaseInfo> findAllByPsNameLikeAndPsType(String name, Integer type,Pageable pageable);
    Page<PSBaseInfo> findAllByPsType(Integer type,Pageable pageable);
}
