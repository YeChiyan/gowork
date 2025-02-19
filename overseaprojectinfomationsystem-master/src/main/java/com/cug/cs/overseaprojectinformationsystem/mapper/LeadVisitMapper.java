package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.LeadVisit;
import java.util.List;

public interface LeadVisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeadVisit row);

    LeadVisit selectByPrimaryKey(Integer id);

    long count();
    List<LeadVisit> selectAll();

    int updateByPrimaryKey(LeadVisit row);

    List<LeadVisit> selectByKey(String key);
    long countByKey(String key);
}