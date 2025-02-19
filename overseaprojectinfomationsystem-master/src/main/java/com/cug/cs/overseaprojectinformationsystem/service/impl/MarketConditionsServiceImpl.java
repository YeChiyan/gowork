package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Infiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.MarketConditions;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.mapper.MarketConditionsMapper;
import com.cug.cs.overseaprojectinformationsystem.service.MarketConditionsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shentong
 * @date 2023/11/13 10:47
 * @description
 */
@Service
public class MarketConditionsServiceImpl implements MarketConditionsService {

    @Autowired
    private MarketConditionsMapper marketConditionsMapper;

    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = marketConditionsMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<MarketConditions> marketConditionsList = marketConditionsMapper.selectAll();
        return new PageBean(marketConditionsList, pageNum, pageSize, count);
    }

    @Override
    public List<String> getOverseaCenter() {
        return marketConditionsMapper.getOverseaCenter();
    }

    @Override
    public List<Integer> getAnnualMetrics(List<String> oveaseaName) {
        List<Integer> num1 = new ArrayList<>();
        Integer n = oveaseaName.size();
        for (int i=0;i<n;i++){
            String name = oveaseaName.get(i);
            num1.add(marketConditionsMapper.getAnnualMetrics(name));
        }
        return num1;
    }

    @Override
    public List<Integer> getTimeSchedule(List<String> oveaseaName) {
        List<Integer> num2 = new ArrayList<>();
        Integer n = oveaseaName.size();
        for (int i=0;i<n;i++){
            String name = oveaseaName.get(i);
            num2.add(marketConditionsMapper.getTimeSchedule(name));
        }
        return num2;
    }

    @Override
    public List<Double> getDone(List<String> oveaseaName) {
        List<Double> num3 = new ArrayList<>();
        Integer n = oveaseaName.size();
        for (int i=0;i<n;i++){
            String name = oveaseaName.get(i);
            Double result = marketConditionsMapper.getDone(name).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
            Double res = Math.round(result*10.0)/10.0;
            num3.add(res);
        }
        return num3;
    }

    @Override
    public List<Integer> getLastYear(List<String> oveaseaName) {
        List<Integer> num4 = new ArrayList<>();
        Integer n = oveaseaName.size();
        for (int i=0;i<n;i++){
            String name = oveaseaName.get(i);
            num4.add(marketConditionsMapper.getLastYear(name));
        }
        return num4;
    }

    @Override
    public boolean add(MarketConditions marketConditions) {
        return marketConditionsMapper.insert(marketConditions) > 0;
    }

    @Override
    public boolean update(MarketConditions marketConditions) {
        return marketConditionsMapper.updateByPrimaryKey(marketConditions) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return marketConditionsMapper.deleteByPrimaryKey(id) > 0;
    }
}
