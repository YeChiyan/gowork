package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Manage;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Outfiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.mapper.ManageMapper;
import com.cug.cs.overseaprojectinformationsystem.service.ManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shentong
 * @date 2023/10/7 20:20
 * @description
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = manageMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Manage> manageList = manageMapper.selectAll();
        return new PageBean(manageList, pageNum, pageSize, count);
    }

    @Override
    public boolean add(Manage manage) {
        return manageMapper.insert(manage) > 0;
    }

    @Override
    public boolean update(Manage manage) {
        return manageMapper.updateByPrimaryKey(manage) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return manageMapper.deleteByPrimaryKey(id) > 0;

    }
}
