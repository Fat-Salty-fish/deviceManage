package com.sinoyd.demo.service;

import com.sinoyd.demo.criteria.DgiInfoCriteria;
import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.repository.DgiInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-04 10:16
 */
@Service
public class DgiInfoService {
    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    //在添加数采仪时设置主板型号以及批次id
    public void create(DgiInfo dgiInfo) {
        dgiInfo.setMainBoardModel(dgiInfo.getDgiCode().substring(0, 4));
        dgiInfoRepository.save(dgiInfo);
    }

    public DgiInfo findByCode(String idOrCode) {
        if (idOrCode.length()==11){
            return dgiInfoRepository.findByDgiCode(idOrCode);
        }
        else {
            return dgiInfoRepository.findById(Integer.getInteger(idOrCode)).orElse(null);
        }
    }

    //获取数采仪的分组信息以及对应分组信息的数量
    public Page<DgiInfo> findByGroup(DgiInfoCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage() - 1, criteria.getRows());
        Page<DgiInfo> page = dgiInfoRepository.findAllByGroup("%" + criteria.getDgiNameOrMainBoardModel() + "%",criteria.getPsId(), pageRequest);
        return page;
    }

    //获取某个批号以及主板对应的数采仪详细信息
    public Page<DgiInfo> findDetailByPage(DgiInfoCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage()-1,criteria.getRows());
        Page<DgiInfo> page = dgiInfoRepository.findDetailInfo(criteria.getPsId(), criteria.getMainBoardModel(),
                                                              criteria.getDgiCodeOrBatchNumber(),criteria.getStartTime(),
                                                              criteria.getEndTime(),criteria.getStatus(),pageRequest);
        return page;
    }

    @Transactional
    public void update(DgiInfo dgiInfo) {
        dgiInfoRepository.save(dgiInfo);
    }

    @Transactional
    public Integer delete(Collection<Integer> ids) {
        return dgiInfoRepository.deleteAllByIdIn(ids);
    }
}
