package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.MarketConditions;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Outfiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shentong
 * @date 2023/11/13 10:44
 * @description
 */

@Transactional
public interface MarketConditionsService {
    /**
     * 查询全部
     */
    public PageBean getAll(PageBaseInfo pageBaseInfo);

    public List<String> getOverseaCenter();

    public List<Integer> getAnnualMetrics(List<String> oveaseaName);

    public List<Integer> getTimeSchedule(List<String> oveaseaName);

    public List<Double> getDone(List<String> oveaseaName);

    public List<Integer> getLastYear(List<String> oveaseaName);



    /**
     * 添加
     */
    public  boolean add(MarketConditions marketConditions);

    /**
     * 修改
     */
    public boolean update(MarketConditions marketConditions);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);
}
