package com.sinoyd.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinoyd.frame.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Table(name = "DgiSale")//数采仪销售单
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class DgiSale implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer psId;//购买企业id
    @CreatedBy
    @Column(updatable = false)
    private String operator;//出库经办人
    private String saleContract;//合同编号
    private Integer totalAmount;//合同总需求量
    private Integer outAmount = 0;//已出库数量
    @Column(length = 4000)
    private String remark;//备注

    public Integer getResidueAmount() {
        return this.getTotalAmount() - this.getOutAmount();
    }

    @Transient
    @Access(AccessType.PROPERTY)
    private Integer difference;

    @CreatedBy
    @Column(updatable = false)
    private String creator;
    @CreatedDate()
    @Column(updatable = false)
    private Date createDate;
    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private Date modifyDate;

    public Integer getDifference() {
        this.difference = this.totalAmount - this.outAmount;
        return difference;
    }

}
