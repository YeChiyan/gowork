package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Event;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Presentation;

import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 技术宣讲
 */
public interface PresentationMapper {
    /**
     * 查询所有
     */
    long count();
    List<Presentation> getAll();

    /**
     * 添加
     */
    int add(Presentation presentation);

    /**
     * 修改
     */
    int update(Presentation presentation);

    /**
     * 删除
     */
    int deleteById(int id);

    Integer gatSpeechNum(String startTime, String endTime, String name);


}