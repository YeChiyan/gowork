package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Outfiling;

import java.util.List;

public interface OutfilingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Outfiling row);

    Outfiling selectByPrimaryKey(Integer id);

    long count();
    List<Outfiling> selectAll();

    int updateByPrimaryKey(Outfiling row);

    int passStatus(Integer id);

    int rejectStatus(Integer id);
}