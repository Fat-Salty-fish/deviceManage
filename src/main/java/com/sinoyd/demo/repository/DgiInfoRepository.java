package com.sinoyd.demo.repository;

import com.sinoyd.demo.entity.DgiInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-04 10:16
 */
public interface DgiInfoRepository extends PagingAndSortingRepository<DgiInfo, Integer>, JpaSpecificationExecutor<DgiInfo>, JpaRepository<DgiInfo, Integer> {
    Integer deleteAllByIdIn(Collection<Integer> ids);

    DgiInfo findByDgiCode(String code);

    //    @Query(value = "select new com.sinoyd.demo.entity.DgiInfo (a.batchId,a.mainBoardModel,a.psId,count(a),b.dgiName) " +
//            " from DgiInfo as a left join ProductBatch as b " +
//            " on a.batchId = b.id " +
//            " where a.status = 1 " +
//            " and (( a.mainBoardModel like :dgiNameOrMainBoardModel or b.dgiName like :dgiNameOrMainBoardModel) " +
//            "or ( :dgiNameOrMainBoardModel = ''))" +
//            " and (a.psId = :psId or :psId = null)" +
//            " group by a.batchId,a.mainBoardModel")
    @Query(value = "select new com.sinoyd.demo.entity.DgiInfo(a.batchId,a.mainBoardModel,a.psId,count(a),b.dgiName,c.psName) " +
            "from DgiInfo as a, ProductBatch as b, PSBaseInfo as c " +
            "where a.batchId = b.id and a.psId = c.id " +
            "and a.status in (1,3,4,6) " +
            "and (( a.mainBoardModel like :dgiNameOrMainBoardModel or b.dgiName like :dgiNameOrMainBoardModel) " +
            "or ( :dgiNameOrMainBoardModel = '')) " +
            "and (a.psId = :psId or :psId = null) " +
            "group by a.batchId,a.mainBoardModel")
    Page<DgiInfo> findAllByGroup(@Param("dgiNameOrMainBoardModel") String dgiNameOrMainBoardModel, @Param("psId") Integer psId, Pageable pageRequest);

    @Query(value = "select new com.sinoyd.demo.entity.DgiInfo(a.id,a.batchId,a.dgiCode,a.mainBoardModel,a.psId,a.status,b.dgiName,b.batchNumber) " +
            "from DgiInfo as a, ProductBatch as b " +
            "where a.batchId = b.id " +                         //关联条件
            "and a.batchId = :batchId " +
            "and a.psId = :psId " +                             //厂商和主板查询条件  这两项为必填项
            "and a.mainBoardModel = :mainBoardModel " +
            "and ( a.dgiCode like :dgiCode or :dgiCode = '') " +    //数采仪编号以及批号模糊检索
            "and ( a.createDate >= :startTime or :startTime = '') " +    //对于开始时间参数的检索
            "and ( a.createDate <= :endTime or :endTime = '') " +        //对于结束时间参数的检索
            "and a.status = :status")
        //需要的参数 psId 企业id mainBoardModel 主板型号 dgiCodeOrBatchNumber 模糊检索 数采仪编号或者批号 startTime 开始时间 endTime 结束时间 status 状态码
    Page<DgiInfo> findDetailInfo(@Param("psId") Integer psId,
                                 @Param("mainBoardModel") String mainBoardModel,
                                 @Param("dgiCode") String dgiCode,
                                 @Param("batchId") Integer batchId,
                                 @Param("startTime") Date startTime,
                                 @Param("endTime") Date endTime,
                                 @Param("status") Integer status,
                                 Pageable pageRequest);
}
