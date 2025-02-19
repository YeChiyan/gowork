package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PotProject;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Presentation;
import com.cug.cs.overseaprojectinformationsystem.mapper.BidMapper;
import com.cug.cs.overseaprojectinformationsystem.service.BidService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Service
public class BidServiceImpl implements BidService {


    @Autowired
    private BidMapper bidMapper;


    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = bidMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Bid> bidList = bidMapper.getAll();
        return new PageBean(bidList, pageNum, pageSize, count);
    }
    @Override
    public boolean add(Bid bid) {
        return bidMapper.add(bid) > 0;
    }

    @Override
    public boolean update(Bid bid) {
        return bidMapper.update(bid) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return bidMapper.deleteById(id) > 0;
    }

    @Override
    public List<Double> getBidMoney(List<String> months) {
        List<Double> BidMoney = new ArrayList<>();
        for (String month:months){
            String startTime = month+"-01 00:00:00";
            String endTime = month+"-31 23:59:59";
            Double money = bidMapper.getBidMoney(startTime,endTime);
            BidMoney.add(money);
        }
        return BidMoney;
    }

    @Override
    public List<Integer> getBidNum(List<String> months) {
        List<Integer> bidNum = new ArrayList<>();
        for (String month:months){
            String startTime = month+"-01 00:00:00";
            String endTime = month+"-31 23:59:59";
            Integer num = bidMapper.getBidNum(startTime,endTime);
            bidNum.add(num);
        }
        return bidNum;
    }

    @Override
    public List<Integer> getJudgeBidNum(List<String> months) {
        List<Integer> judgeBidNum = new ArrayList<>();
        for (String month:months){
            String startTime = month+"-01 00:00:00";
            String endTime = month+"-31 23:59:59";
            Integer num = bidMapper.getJudgeBidNum(startTime,endTime);
            judgeBidNum.add(num);
        }
        return judgeBidNum;
    }



    @Override
    public List<Integer> getBidNumforMonth(List<String> months) {
        List<Integer> bidNum = new ArrayList<>();
        for (String month:months){
            String startTime = month +"-01 00:00:00";
            String endTime = month +"-31 23:59:59";
            Integer num = bidMapper.getBidNum(startTime,endTime);
            bidNum.add(num);
        }
        return bidNum;
    }

    @Override
    public List<Integer> getAllBidNumforMonth(List<String> months) {
        List<Integer> bidNum = new ArrayList<>();
        for (String month:months){
            String startTime = month+"-01 00:00:00";
            String endTime = month+"-31 23:59:59";
            Integer num = bidMapper.getAllBidNum(startTime,endTime);
            bidNum.add(num);
        }
        return bidNum;
    }

    @Override
    public List<Integer> getBidNumEachMonth(ArrayList<String> centerName) {
        List<Integer> bidNumforMonth = new ArrayList<>();
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue() - 1 ;
        if(month == 0){
            year--;
            month=12;
        }
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        String startTime = year+"-"+month+"-01 00:00:00";
        String endTime = year+"-"+month+"-"+daysInMonth+" 23:59:59";
        for (String name:centerName){
            bidNumforMonth.add(bidMapper.getBidNumEachMonth(startTime,endTime,name));
        }
        return bidNumforMonth;

    }


}
