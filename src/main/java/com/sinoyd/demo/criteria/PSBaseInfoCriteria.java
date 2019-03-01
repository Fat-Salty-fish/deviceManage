package com.sinoyd.demo.criteria;

import com.sinoyd.frame.base.util.BaseCriteria;
import com.sinoyd.frame.base.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用于企业信息分页搜索的搜索条件
 * @auther 李忠杰
 * @create 2019-02-27 14:22
 */
@Getter
@Setter
public class PSBaseInfoCriteria {
    private String psName;  //企业名称
    private Integer psType; //企业类型 1位生产厂商 2为客户

    private Integer pageNumber = 0;  //页码 默认第0页
    private Integer pageSize = 10;  //每页数据量 默认为10

}
