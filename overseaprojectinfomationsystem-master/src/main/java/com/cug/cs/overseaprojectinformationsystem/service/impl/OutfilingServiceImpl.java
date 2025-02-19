package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Infiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Outfiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.mapper.OutfilingMapper;
import com.cug.cs.overseaprojectinformationsystem.service.OutfilingService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shentong
 * @date 2023/10/7 20:02
 * @description
 */
@Service
public class OutfilingServiceImpl implements OutfilingService {
    @Autowired
    private OutfilingMapper outfilingMapper;

    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = outfilingMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Outfiling> outfilingList = outfilingMapper.selectAll();
        return new PageBean(outfilingList, pageNum, pageSize, count);
    }

    @Override
    public boolean add(Outfiling outfiling) {
        return outfilingMapper.insert(outfiling) > 0;
    }

    @Override
    public boolean update(Outfiling outfiling) {
        return outfilingMapper.updateByPrimaryKey(outfiling) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return outfilingMapper.deleteByPrimaryKey(id) > 0;

    }

    //审核通过
    public int outfilingPass(int id){
        int outfiling = outfilingMapper.passStatus(id);
        return 2;
    }

    //审核不通过
    public int outfilingReject(int id){
        int outfiling = outfilingMapper.rejectStatus(id);
        return 3;
    }
}
