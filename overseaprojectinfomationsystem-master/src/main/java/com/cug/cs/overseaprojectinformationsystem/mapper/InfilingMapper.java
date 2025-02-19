package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Infiling;

import java.util.List;

public interface InfilingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Infiling row);

    Infiling selectByPrimaryKey(Integer id);

    long count();
    List<Infiling> selectAll();

    int updateByPrimaryKey(Infiling row);
    //Infiling updateByPrimaryKey(Infiling row);

    int passStatus(Integer id);

    int rejectStatus(Integer id);
}