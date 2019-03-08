package com.sinoyd.demo.criteria;

import com.sinoyd.frame.base.util.BaseCriteria;
import com.sinoyd.frame.base.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-01 17:08
 */
@Getter
@Setter
public class ProductBatchCriteria extends BaseCriteria {

    private String batchNumber; //批号名字
    private String dgiName;     //数采仪名字
    private Integer psId;        //企业id
    private Integer status;    //0停用,1启用

    @Override
    public String getCondition() {
        StringBuilder condition = new StringBuilder();
        values.clear();
        if (StringUtils.isNotNullAndEmpty(this.batchNumber)) {
            condition.append(" and batchNumber like :batchNumber ");
            values.put("batchNumber", "%" + this.batchNumber + "%");
        }
        if (StringUtils.isNotNullAndEmpty(this.dgiName)) {
            condition.append(" and dgiName like :dgiName ");
            values.put("dgiName", "%" + this.dgiName + "%");
        }
        if (psId != null) {
            condition.append(" and psId = :psId ");
            values.put("psId", this.psId);
        }
        if (status != null) {
            condition.append(" and status = :status");
            values.put("status", this.status);
        }
        return condition.toString();
    }
}
