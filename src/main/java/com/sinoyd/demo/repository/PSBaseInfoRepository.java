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
public interface PSBaseInfoRepository extends JpaRepository<PSBaseInfo,Integer>{
    List<PSBaseInfo> findAllByIdIn(Collection<Integer> ids);
    Page<PSBaseInfo> findAllByPsNameLikeAndPsTypeAndIsDeleted(String name, Integer type,Boolean isDeleted,Pageable pageable);
    Page<PSBaseInfo> findAllByPsTypeAndIsDeleted(Integer type,Boolean isDeleted,Pageable pageable);
    Page<PSBaseInfo> findAllByIsDeleted(Boolean isDeleted,Pageable pageable);
    Page<PSBaseInfo> findAllByPsNameLikeAndIsDeleted(String name,Boolean isDeleted,Pageable pageable);
}
