package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PotProject;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Presentation;
import com.cug.cs.overseaprojectinformationsystem.mapper.PotproMapper;
import com.cug.cs.overseaprojectinformationsystem.service.PotproService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @className: PotproServiceImpl
 * @author: Zhangz
 * @description: TODO
 * @date: 2023/9/15 22:22
 * @version: 1.0
 */
@Service
public class PotproServiceImpl implements PotproService {
    @Autowired
    private PotproMapper potproMapper;
    //添加潜在项目
    @Override
    public void addPotproject(PotProject potProject) {
        potproMapper.addPotproject(potProject);
    }

    @Override
    public PageBean selectPotproject(PageBaseInfo pageBaseInfo) {
        long count = potproMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<PotProject> potProjectList = potproMapper.selectPotproject();
        return new PageBean(potProjectList, pageNum, pageSize, count);
    }

    @Override
    public void deletePotproject(List<Integer> projectIds) {
        potproMapper.deletePotproject(projectIds);
    }
    @Override
    public void deletePotproject(PotProject potProject) {
        potproMapper.deletePotproject1(potProject);
    }
    @Override
    public boolean delete(Integer projectId) {
        return potproMapper.deleteByPrimaryKey(projectId) > 0;

    }

    @Override
    public void updatePotproject(PotProject potProject) {
        potproMapper.updatePotproject(potProject);
    }

    @Override
    public List<Integer> getPotNum(ArrayList<String>  centerName) {
        List<Integer> potPorNum = new ArrayList<>();
        for (String name : centerName) {
            potPorNum.add(potproMapper.getPotNum(name));
        }
        return potPorNum;
    }
}
