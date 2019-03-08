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

@Table(name = "DgiApply")//数采仪领用记录
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class DgiApply implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Date applyDate;//领用日期
    private String operator;//领用人
    private Integer dgiId;//数采仪id
    private Integer statusBefore;//领用之前的状态
    private Integer applyPurpose;//领用目的 1-测试 2-维修
    @Column(length = 4000)
    private String remark;//备注

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
