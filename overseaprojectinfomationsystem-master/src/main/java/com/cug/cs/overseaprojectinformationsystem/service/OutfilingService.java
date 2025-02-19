package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Outfiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shentong
 * @date 2023/10/7 20:00
 * @description
 */
@Transactional
public interface OutfilingService {
    /**
     * 查询全部
     */
    public PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
     * 添加
     */
    public  boolean add(Outfiling outfiling);

    /**
     * 修改
     */
    public boolean update(Outfiling outfiling);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);

    /**
     * 审核通过
     * @param id
     * @return
     */
    public int outfilingPass(int id);

    /**
     * 审核不通过
     * @param id
     * @return
     */
    public int outfilingReject(int id);
}
