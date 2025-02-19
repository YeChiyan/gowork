package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Event;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Transactional
public interface EventService {
    /**
     * 查询全部
     */
    public PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
     * 添加
     */
    public  boolean add(Event event);

    /**
     * 修改
     */
    public boolean update(Event event);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);

//    获得参观拜访数
    List<Integer> getVisitNum(ArrayList<String> centerName);
}
