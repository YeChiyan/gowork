package com.cug.cs.overseaprojectinformationsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Presentation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExcelPreDao
 * @Description TODO
 * @Author Zhangz
 * @Date 2023/9/20 20:37
 * @Version 1.0
 **/
@Mapper
@Component
public interface ExcelPreDao extends BaseMapper<Presentation> {
}
