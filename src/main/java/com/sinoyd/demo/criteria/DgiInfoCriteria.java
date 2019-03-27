package com.sinoyd.demo.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinoyd.frame.base.util.BaseCriteria;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-04 10:41
 */
@Getter
@Setter
public class DgiInfoCriteria {
    //这两个条件用于库存显示分组情况时
    private String dgiNameOrMainBoardModel = ""; //数采仪主板型号或者数采仪名称
    private Integer psId;      //数采仪生产厂商   这个参数同时会用在库存详细情况条件查询

    //以下条件用于库存详细情况时
    private String mainBoardModel = "";         //数采仪主板型号 在库存详细情况查询时 此条件与生产厂商id为必填 这两个参数可以确定一批产品
    private String dgiCode = "";   //数采仪编号或者生产批号
    private Integer batchId;            //数采仪批号 用于显示右边的详情
    private String startTime = "";                    //开始时间
    private String endTime = "";                      //结束时间
    private Integer status;                    //数采仪状态

    //以下条件用于设置分页的格式 第几页以及每页的数量
    private Integer page = 1;   //页码 默认第1页
    private Integer rows = 10;  //每页数据量 默认为10

    @Override
    public String toString() {
        return "DgiInfoCriteria{" +
                "dgiNameOrMainBoardModel='" + dgiNameOrMainBoardModel + '\'' +
                ", psId=" + psId +
                ", mainBoardModel='" + mainBoardModel + '\'' +
                ", dgiCode='" + dgiCode + '\'' +
                ", batchId=" + batchId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
