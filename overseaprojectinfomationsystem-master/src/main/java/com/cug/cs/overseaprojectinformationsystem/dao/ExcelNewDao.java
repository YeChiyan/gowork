package com.cug.cs.overseaprojectinformationsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.NewSign;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExcelNewDao
 * @Description TODO
 * @Author Zhangz
 * @Date 2023/9/20 20:37
 * @Version 1.0
 **/
@Mapper
@Component
public interface ExcelNewDao extends BaseMapper<NewSign> {
}
