package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Infiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.mapper.InfilingMapper;
import com.cug.cs.overseaprojectinformationsystem.service.InfilingService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shentong
 * @date 2023/10/7 15:50
 * @description
 */
@Service
public class InfilingServiceImpl implements InfilingService {

    @Autowired
    private InfilingMapper infilingMapper;

    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = infilingMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Infiling> infilingList = infilingMapper.selectAll();
        return new PageBean(infilingList, pageNum, pageSize, count);
    }

    @Override
    public boolean add(Infiling infiling) {
        return infilingMapper.insert(infiling) > 0 ;
    }

    @Override
    public boolean update(Infiling infiling) {
        return infilingMapper.updateByPrimaryKey(infiling) > 0 ;
    }

    @Override
    public boolean delete(Integer id) {
        return infilingMapper.deleteByPrimaryKey(id) > 0 ;
    }

    //提交审核
    public int submitInfiling(Infiling infiling){
        infiling.setApprovalStatus(1); //默认状态为待审核
        return infiling.getApprovalStatus();
    }

    //审核通过
    public int infilingPass(int id){
        int infiling = infilingMapper.passStatus(id);
        return 2;
    }

    //审核不通过
    public int infilingReject(int id){
        int infiling = infilingMapper.rejectStatus(id);
        return 3;
    }
}
