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

@Table(name = "ProductBatch")//生产批号
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class ProductBatch implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String batchNumber;//批号名字
    private String dgiName;//数采仪名字
    private String psId;//企业id
    private Integer status = 1;//0停用,1启用

    @CreatedBy
    private String creator;
    @CreatedDate
    private Date createDate;
    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private Date modifyDate;
}
