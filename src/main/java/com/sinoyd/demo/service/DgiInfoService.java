package com.sinoyd.demo.service;

import com.sinoyd.demo.criteria.DgiInfoCriteria;
import com.sinoyd.demo.entity.DgiInfo;
import com.sinoyd.demo.entity.PSBaseInfo;
import com.sinoyd.demo.entity.ProductBatch;
import com.sinoyd.demo.repository.DgiInfoRepository;
import com.sinoyd.demo.repository.PSBaseInfoRepository;
import com.sinoyd.demo.repository.ProductBatchRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Description
 * @auther 李忠杰
 * @create 2019-03-04 10:16
 */
@Service
public class DgiInfoService {

    @Autowired
    private DgiInfoRepository dgiInfoRepository;

    @Autowired
    private ProductBatchRepository productBatchRepository;

    @Autowired
    private PSBaseInfoRepository psBaseInfoRepository;


    //在添加数采仪时设置主板型号以及批次id
    public DgiInfo create(DgiInfo dgiInfo) {
        String batchCode = productBatchRepository.getOne(dgiInfo.getBatchId()).getBatchNumber();
        if (!dgiInfo.getDgiCode().substring(4, 8).equals(batchCode)) {
            throw new IllegalArgumentException("输入的批次号和已知的批次号不符");
        }
        if (dgiInfoRepository.findByDgiCode(dgiInfo.getDgiCode()) == null) {
            dgiInfo.setMainBoardModel(dgiInfo.getDgiCode().substring(0, 4));
            dgiInfoRepository.save(dgiInfo);
            DgiInfo result = dgiInfoRepository.findByDgiCode(dgiInfo.getDgiCode());
            result.setBatchCode(batchCode);
            return result;
        } else {
            throw new IllegalArgumentException("此数采仪已经存在 无法重复添加");
        }
    }

    //根据id或dgiCode获取数采仪全部信息 如果输入的参数为11为 则认为传入的是dgiCode 否则认为传入的是id
    //返回给前端时同时要注入数采仪名称与数采仪批号
    public DgiInfo findByCode(String idOrCode) {
        DgiInfo result;
        ProductBatch batch;
        PSBaseInfo psBaseInfo;
        if (idOrCode.length() == 11) {
            result = dgiInfoRepository.findByDgiCode(idOrCode);
        } else {
            Integer id = Integer.valueOf(idOrCode);
            result = dgiInfoRepository.getOne(id);
        }
        if(result == null){
            return null;
        }
        batch = productBatchRepository.findById(result.getBatchId()).orElse(null);
        psBaseInfo = psBaseInfoRepository.findById(result.getPsId()).orElse(null);
        result.setBatchCode(batch.getBatchNumber());
        result.setDgiName(batch.getDgiName());
        result.setPsName(psBaseInfo.getPsName());
        result.setContactMan(psBaseInfo.getContactMan());
        result.setContactTelPhone(psBaseInfo.getContactTelPhone());
        return result;
    }

    //获取数采仪的分组信息以及对应分组信息的数量
    public Page<DgiInfo> findByGroup(DgiInfoCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage() - 1, criteria.getRows());
        Page<DgiInfo> page = dgiInfoRepository.findAllByGroup("%" + criteria.getDgiNameOrMainBoardModel() + "%", criteria.getPsId(), pageRequest);
        return page;
    }

    //获取某个批号以及主板对应的数采仪详细信息
    public Page<DgiInfo> findDetailByPage(DgiInfoCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage() - 1, criteria.getRows());
        String startDate = criteria.getStartTime();
        String endDate = criteria.getEndTime();
        if (StringUtils.isBlank(startDate)) {
            startDate = "1970-01-01";
        }
        if (StringUtils.isBlank(endDate)) {
            endDate = "9999-12-31";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Page<DgiInfo> page = null;
        try {
            page = dgiInfoRepository.findDetailInfo(
                    criteria.getPsId(),
                    criteria.getMainBoardModel(),
                    "%" + criteria.getDgiCode() + "%",
                    criteria.getBatchId(),
                    simpleDateFormat.parse(startDate),
                    simpleDateFormat.parse(endDate),
                    criteria.getStatus(), pageRequest);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return page;
    }

    //更新数采仪信息
    @Transactional
    public void update(DgiInfo dgiInfo) {
        dgiInfoRepository.save(dgiInfo);
    }

    //删除某个数采仪信息
    @Transactional
    public void delete(Integer dgiId) {
        dgiInfoRepository.deleteById(dgiId);
    }
}
