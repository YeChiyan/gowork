package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Transactional

public interface BidService {
    /**
     * 查询全部
     */
    PageBean getAll(PageBaseInfo pageBaseInfo);

    /**
     * 添加
     */
    public  boolean add(Bid bid);

    /**
     * 修改
     */
    public boolean update(Bid bid);

    /**
     * 按id删除
     */
    public boolean delete(Integer id);


    List<Double> getBidMoney(List<String> months);
    List<Integer> getBidNum(List<String> months);

    List<Integer> getJudgeBidNum(List<String> months);


    List<Integer> getBidNumforMonth(List<String> months);

    List<Integer> getAllBidNumforMonth(List<String> months);

    //获得每中心月度投标量
    List<Integer> getBidNumEachMonth(ArrayList<String> centerName);
}
