package com.cug.cs.overseaprojectinformationsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Manage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName ManageExportDao
 * @Description
 * @Author Zhangz
 * @Date 2023/11/24 10:24
 * @Version 1.0
 **/
@Mapper
@Component
public interface ManageExportDao extends BaseMapper<Manage> {
}
