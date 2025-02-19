package com.cug.cs.overseaprojectinformationsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PotProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExcelDao
 * @Description TODO
 * @Author Zhangz
 * @Date 2023/9/20 15:59
 * @Version 1.0
 **/
@Mapper
@Component
public interface ExcelPotDao extends BaseMapper<PotProject>{
}