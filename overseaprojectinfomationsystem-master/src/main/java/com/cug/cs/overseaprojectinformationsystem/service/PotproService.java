package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.bean.common.ResponseUtil;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PotProject;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: PotproService
 * @author: Zhangz
 * @description: TODO
 * @date: 2023/9/15 22:22
 * @version: 1.0
 */
public interface PotproService {

    //实现潜在项目录入功能
    void addPotproject (PotProject potProject);
    //分页查询
    PageBean selectPotproject(PageBaseInfo pageBaseInfo);
    //删除员工
    void deletePotproject(PotProject potProject);
    void deletePotproject(List<Integer> projectIds);
    public boolean delete(Integer projectId);
    //更新项目信息
    void updatePotproject(PotProject potProject);

    List<Integer> getPotNum(ArrayList<String>  centerName);
}
