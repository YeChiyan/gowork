package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Infiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shentong
 */
@Transactional
public interface InfilingService {
    /**
     * 查询全部
     */
    public PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
     * 添加
     */
    public  boolean add(Infiling infiling);

    /**
     * 修改
     */
    public boolean update(Infiling infiling);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);

    /**
     * 提交审核
     * @param infiling
     * @return
     */
    public int submitInfiling(Infiling infiling);

    /**
     * 审核通过
     * @param id
     * @return
     */
    public int infilingPass(int id);

    /**
     * 审核不通过
     * @param id
     * @return
     */
    public int infilingReject(int id);
}
