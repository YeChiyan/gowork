package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Manage;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shentong
 * @date 2023/10/7 20:19
 * @description
 */
@Transactional
public interface ManageService {
    /**
     * 查询全部
     */
    public PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
     * 添加
     */
    public  boolean add(Manage manage);

    /**
     * 修改
     */
    public boolean update(Manage manage);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);
}
