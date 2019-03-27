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

@Table(name = "DgiRepair")//数采仪维修信息
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class DgiRepair implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    @Column(updatable        = false)
    private Date repairDate;//维修日期
    @CreatedBy
    @Column(updatable = false)
    private String operator;//维修人
    private Integer dgiId;//数采仪id
    private Integer result;//维修结果(1.通过;2.不通过)
    @Column(length = 4000)
    private String destroyCause;//维修原因
    @Column(length = 4000)
    private String repairExplain;//维修说明
    private Integer statusBefore;//维修之前的状态

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
