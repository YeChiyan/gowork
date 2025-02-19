package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Event;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.mapper.EventMapper;
import com.cug.cs.overseaprojectinformationsystem.service.EventService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;


    /*@Override
    public PageBean getAll(Integer page, Integer pageSize) {
        long count = eventMapper.count();
        //2.获取分页查询结果列表
        Integer start = (page-1)*pageSize;//计算起始索引
        List<Event> empList = eventMapper.getAll(start,pageSize);
        Collections.reverse(empList);
        //3.封装PageBean对象
        return new PageBean(count,empList);
    }*/

    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = eventMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Event> eventList = eventMapper.getAll();
        return new PageBean(eventList, pageNum, pageSize, count);
    }

    @Override
    public boolean add(Event event) {
        return eventMapper.add(event) > 0;
    }

    @Override
    public boolean update(Event event) {
        return eventMapper.update(event) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return eventMapper.deleteById(id) > 0;
    }

    @Override
    public List<Integer> getVisitNum(ArrayList<String> centerName) {
        // 1、先获取当前时间的月份及天数
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue() - 1;
        if(month == 0){
            year--;
            month=12;
        }
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        String startTime = year+"-"+month+"-01 00:00:00";
        String endTime = year+"-"+month+"-"+daysInMonth+" 23:59:59";
        List<Integer> visitNum = new ArrayList<>();
        for (String name : centerName) {
            visitNum.add(eventMapper.getEventNum(startTime, endTime, name));
        }
        return visitNum;
    }
}
