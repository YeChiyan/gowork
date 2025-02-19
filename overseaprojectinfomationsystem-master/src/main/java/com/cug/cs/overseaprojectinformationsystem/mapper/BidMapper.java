package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;

import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 投标统计
 */
public interface BidMapper {
    /**
     * 分页查询
     * @return
     */
    long count();
    List<Bid> getAll();

    /**
     * 添加
     */
    int add(Bid bid);

    /**
     * 修改
     */
    int update(Bid bid);

    /**
     * 删除
     */
    int deleteById(int id);

    Double getBidMoney(String startTime,String endTime);

    Integer getBidNumEachMonth(String startTime, String endTime,String name);

    Integer getBidNum(String startTime,String endTime);

    Integer getJudgeBidNum(String startTime, String endTime);

    Integer getAllBidNum(String startTime, String endTime);




}