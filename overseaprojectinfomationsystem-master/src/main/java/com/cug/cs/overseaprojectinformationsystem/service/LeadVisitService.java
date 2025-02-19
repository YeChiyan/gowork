package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Infiling;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.LeadVisit;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.MarketConditions;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shentong
 * @date 2023/11/20 10:26
 * @description
 */

@Transactional
public interface LeadVisitService {

    /**
     * 查询
     */
    public PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
    * 按关键字查询
    */
    public PageBean getByKey(PageBaseInfo pageBaseInfo);
    /**
     * 添加
     */
    public  boolean add(LeadVisit leadVisit);

    /**
     * 修改
     */
    public boolean update(LeadVisit leadVisit);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);
}
