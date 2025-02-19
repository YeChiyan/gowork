package com.cug.cs.overseaprojectinformationsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Event;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExcelEventDao
 * @Description TODO
 * @Author Zhangz
 * @Date 2023/9/20 20:35
 * @Version 1.0
 **/
@Mapper
@Component
public interface ExcelEventDao extends BaseMapper<Event> {
}
