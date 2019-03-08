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

@Table(name = "DgiTest")//数采仪测试信息
@Entity
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class DgiTest implements BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer dgiId;//数采仪id
    private String operator;//测试人
    private Date testDate;//测试日期
    private Integer result;//测试结果(1.通过;2.不通过)
    @Column(length = 4000)
    private String testExplain;//测试说明

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
