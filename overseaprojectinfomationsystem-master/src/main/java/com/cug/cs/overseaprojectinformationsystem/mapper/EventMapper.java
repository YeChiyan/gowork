package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Event;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 市场营销
 */
public interface EventMapper {


    Integer getEventNum(String startTime, String endTime, String name);

    /**
     * 查询所有
     */
    List<Event> getAll();

    long count();
    /**
     * 添加
     */
    int add(Event event);

    /**
     * 修改
     */
    int update(Event event);

    /**
     * 删除
     */
    int deleteById(int id);

}
