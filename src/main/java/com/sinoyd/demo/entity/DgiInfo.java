package com.sinoyd.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinoyd.frame.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name = "DgiInfo")//数采仪基本信息
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class DgiInfo implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String psId;//厂商企业id
    private String batchId;//批次id
    private String dgiCode;//数采仪编号
    private String dgiMN;//数采仪MN号
    private String mainBoardModel;//主板型号(1-4主板型号)
    private Integer status = 1;//状态(1-已入库2-测试中3-测试不通过4-测试通过5-维修中6-维修完成7-已出库8-已报废)
    private String operator;//入库经办人
    private Date getDate;//入库日期

    @CreatedBy
    private String creator;
    @CreatedDate
    private Date createDate;
    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private Date modifyDate;
}
