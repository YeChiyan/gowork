package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Event;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Presentation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Transactional
public interface PresentationService {
    /**
     * 查询全部
     */
    PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
     * 添加
     */
    public  boolean add(Presentation presentation);

    /**
     * 修改
     */
    public boolean update(Presentation presentation);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);

//    获得演讲数量
    List<Integer> getSpeechNum(ArrayList<String> centerName);
}
