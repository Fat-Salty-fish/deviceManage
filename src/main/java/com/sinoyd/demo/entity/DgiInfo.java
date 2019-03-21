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
    private Integer psId;//厂商企业id
    private Integer batchId;//批次id
    private String dgiCode;//数采仪编号
    private String dgiMN;//数采仪MN号
    private String mainBoardModel;//主板型号(1-4主板型号)
    private Integer status = 1;//状态(1-已入库2-测试中3-测试不通过4-测试通过5-维修中6-维修完成7-已出库8-已报废)
    private String operator;//入库经办人
    private Date getDate;//入库日期

    @Transient
    private Long count;  //用来表示某个批次和主板型号下的数采仪个数

    @Transient
    private String psName;  //企业名称 用于前端展示
    @Transient
    private String dgiName; //数采仪名称 用于前端展示

    @Transient
    private String batchCode;   //数采仪批次号 用于前端展示


    public DgiInfo(Integer batchId, String mainBoardModel, Integer psId, Long count, String dgiName, String psName) {
        this.batchId = batchId;
        this.mainBoardModel = mainBoardModel;
        this.psId = psId;
        this.count = count;
        this.status = null;
        this.dgiName = dgiName;
        this.psName = psName;
    }

    public DgiInfo(Integer id, Integer batchId, String mainBoardModel, Integer psId, String dgiName) {
        this.id = id;
        this.batchId = batchId;
        this.mainBoardModel = mainBoardModel;
        this.psId = psId;
        this.dgiName = dgiName;
    }


    public DgiInfo() {
        super();
    }

    @CreatedBy
    @Column(updatable = false)
    private String creator;
    @CreatedDate
    @Column(updatable = false)
    private Date createDate;
    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private Date modifyDate;
}
