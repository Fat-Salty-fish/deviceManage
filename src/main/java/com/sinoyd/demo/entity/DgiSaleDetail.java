package com.sinoyd.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

@Table(name = "DgiSaleDetail")//数采仪销售明细
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class DgiSaleDetail implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer saleId;//销售id
    private Integer dgiId;//数采仪id
    @CreatedDate
    @Column(updatable = false)
    private Date saleDate;//出库日期
    private Integer statusBefore;//数采仪之前的状态

    @Transient
    private String psName;//企业名称
    @Transient
    private String psRegion;//所在地区
    @Transient
    private String psAddress;//详细地址
    @Transient
    private String contactMan;//联系人
    @Transient
    private Long contactTelPhone;//联系方式
    @Transient
    private String operator;//出库经办人
    @Transient
    private String saleContract;//合同编号

    @Override
    public String toString() {
        return "DgiSaleDetail{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", dgiId=" + dgiId +
                ", saleDate=" + saleDate +
                ", statusBefore=" + statusBefore +
                ", creator='" + creator + '\'' +
                ", createDate=" + createDate +
                ", modifier='" + modifier + '\'' +
                ", modifyDate=" + modifyDate +
                '}';
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
