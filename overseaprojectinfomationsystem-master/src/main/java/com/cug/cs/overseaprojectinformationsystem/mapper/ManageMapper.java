package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Manage;

import java.util.List;

public interface ManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manage row);

    Manage selectByPrimaryKey(Integer id);

    long count();
    List<Manage> selectAll();

    int updateByPrimaryKey(Manage row);
}