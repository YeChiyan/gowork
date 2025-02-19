package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Infiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.LeadVisit;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.mapper.LeadVisitMapper;
import com.cug.cs.overseaprojectinformationsystem.service.LeadVisitService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shentong
 * @date 2023/11/20 10:28
 * @description
 */

@Service
public class LeadVisitServiceImpl implements LeadVisitService {

    @Autowired
    private LeadVisitMapper leadVisitMapper;

    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = leadVisitMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<LeadVisit> leadVisitList = leadVisitMapper.selectAll();
        return new PageBean(leadVisitList, pageNum, pageSize, count);
    }

    @Override
    public PageBean getByKey(PageBaseInfo pageBaseInfo) {
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        String key = pageBaseInfo.getKey();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        if (key != null) {
            List<LeadVisit> leadVisitList = leadVisitMapper.selectByKey(key);
            long count = leadVisitMapper.countByKey(key);
            return new PageBean(leadVisitList, pageNum, pageSize, count);
        }else {
            List<LeadVisit> leadVisitList = leadVisitMapper.selectAll();
            long count = leadVisitMapper.count();
            return new PageBean(leadVisitList, pageNum, pageSize, count);
        }
    }

    @Override
    public boolean add(LeadVisit leadVisit) {
        return leadVisitMapper.insert(leadVisit) > 0;
    }

    @Override
    public boolean update(LeadVisit leadVisit) {
        return leadVisitMapper.updateByPrimaryKey(leadVisit) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return leadVisitMapper.deleteByPrimaryKey(id) > 0;
    }
}
