package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.MarketConditions;

import java.math.BigDecimal;
import java.util.List;

public interface MarketConditionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarketConditions row);

    MarketConditions selectByPrimaryKey(Integer id);

    long count();
    List<MarketConditions> selectAll();

    List<String> getOverseaCenter();

    Integer getAnnualMetrics(String name);

    Integer getTimeSchedule(String name);

    BigDecimal getDone(String name);

    Integer getLastYear(String name);

    int updateByPrimaryKey(MarketConditions row);
}