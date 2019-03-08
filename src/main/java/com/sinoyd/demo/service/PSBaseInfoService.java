package com.sinoyd.demo.service;

import com.sinoyd.demo.criteria.PSBaseInfoCriteria;
import com.sinoyd.demo.entity.PSBaseInfo;
import com.sinoyd.demo.repository.PSBaseInfoRepository;
import com.sinoyd.frame.base.repository.CommonRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Description
 * @auther 李忠杰
 * @create 2019-02-27 13:56
 */
@Service
public class PSBaseInfoService {

    @Autowired
    private PSBaseInfoRepository psBaseInfoRepository;

    public void create(PSBaseInfo companyInfo) {
        psBaseInfoRepository.save(companyInfo);
    }

    public PSBaseInfo findById(Integer companyId) {
        return psBaseInfoRepository.findById(companyId).orElse(null);
    }

    public Page findByPage(PSBaseInfoCriteria criteria) {
        //分页条件 PageRequest
        PageRequest pageRequest = PageRequest.of(criteria.getPage()-1, criteria.getRows());
        //查询条件
        String psName = criteria.getPsName();
        Integer psType = criteria.getPsType();
        if (StringUtils.isNotEmpty(psName)) {
            return psBaseInfoRepository.findAllByPsNameLikeAndPsType("%" + criteria.getPsName() + "%", criteria.getPsType(), pageRequest);
        } else if (psType != null) {
            return psBaseInfoRepository.findAllByPsType(psType, pageRequest);
        } else {
            return psBaseInfoRepository.findAll(pageRequest);
        }
    }

    @Transactional
    public void update(PSBaseInfo companyInfo) {
        if (companyInfo.getId() == null) {
            throw new NullPointerException("公司id为空 无法更新信息");
        }
        psBaseInfoRepository.save(companyInfo);
    }

    @Transactional
    public Integer delete(Collection<Integer> companyeInfoIds) {
        List<PSBaseInfo> companyInfos = psBaseInfoRepository.findAllByIdIn(companyeInfoIds);
        int deleteNum = (int) companyInfos.stream().filter(company -> !company.getIsDeleted()).peek(company -> company.setIsDeleted(true)).count();
        return deleteNum;
    }
}
