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

@Table(name = "DgiSaleDetail")//数采仪销售明细
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class DgiSaleDetail implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer saleId;//销售id
    private Integer dgiId;//数采仪id
    private Date saleDate;//出库日期


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
