package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.NewSign;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Transactional
public interface NewSignService {
    /**
     * 查询全部
     */
    PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
     * 按分中心查询
     */
    PageBean selectByCenter(PageBaseInfo pageBaseInfo);

    /**
     * 添加
     */
    public  boolean add(NewSign newSign);

    /**
     * 修改
     */
    public boolean update(NewSign newSign);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);

//    获得近12个月的总签额
    List<Double> getNewSignMoneyforMonth(List<String> months);

    List<BigDecimal> getWorkload(String contractNum);

}
