package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.NewSign;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Presentation;
import com.cug.cs.overseaprojectinformationsystem.mapper.BidMapper;
import com.cug.cs.overseaprojectinformationsystem.mapper.NewSignMapper;
import com.cug.cs.overseaprojectinformationsystem.service.BidService;
import com.cug.cs.overseaprojectinformationsystem.service.NewSignService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Service
public class NewSignServiceImpl implements NewSignService {

    @Autowired
    private NewSignMapper newSignMapper;


    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = newSignMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<NewSign> newSignList = newSignMapper.getAll();
        return new PageBean(newSignList, pageNum, pageSize, count);
    }

    @Override
    public PageBean selectByCenter(PageBaseInfo pageBaseInfo) {
        String name = pageBaseInfo.getKey();
        long count = newSignMapper.countByCenter(name);
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<NewSign> newSignList = newSignMapper.selectByCenter(name);
        return new PageBean(newSignList, pageNum, pageSize, count);
    }

    @Override
    public boolean add(NewSign newSign) {
        return newSignMapper.add(newSign) > 0;
    }

    @Override
    public boolean update(NewSign newSign) {
        return newSignMapper.update(newSign) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return newSignMapper.deleteById(id) > 0;
    }

    @Override
    public List<Double> getNewSignMoneyforMonth(List<String> months) {
        List<Double> bidMoney = new ArrayList<>();
        for (String year : months){
            String startTime = year+"-01 00:00:00";
            String endTime = year+"-31 23:59:59";
            Double money = newSignMapper.getNewSignMoney(startTime,endTime);
            bidMoney.add(money);
        }
        return bidMoney;
    }

    @Override
    public List<BigDecimal> getWorkload(String contractNum) {
        List<BigDecimal> workList = new ArrayList<>();
        BigDecimal wl2 = newSignMapper.get2d(contractNum);
        BigDecimal wl3 = newSignMapper.get3d(contractNum);
        workList.add(wl2);
        workList.add(wl3);
        return workList;
    }
}