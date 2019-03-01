package com.sinoyd.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinoyd.frame.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.*;

@Table(name = "PsBaseInfo")//企业信息
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class PSBaseInfo implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String psName;//企业名称
    private String psRegion;//所在地区
    private String psAddress;//详细地址
    private String contactMan;//联系人
    private String contactTelPhone;//联系方式
    private String corporationCode;//企业编号

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "company_type",
            joinColumns = {@JoinColumn(name = "company_id",referencedColumnName = "id")})
    @Column(name = "company_type")
    private List<Integer> psType;//企业类别 1-生产厂商 2-客户

    private Boolean isDeleted = false;//软删除标志,1-已删除,0-未删除


    @CreatedBy
    private String creator;
    @CreatedDate
    private Date createDate;
    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private Date modifyDate;
}
