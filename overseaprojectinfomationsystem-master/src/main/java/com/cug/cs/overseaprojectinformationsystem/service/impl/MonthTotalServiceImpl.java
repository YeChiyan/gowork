package com.cug.cs.overseaprojectinformationsystem.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.*;
import com.cug.cs.overseaprojectinformationsystem.mapper.MonthTotalMapper;
import com.cug.cs.overseaprojectinformationsystem.mapper.NewSignMapper;
import com.cug.cs.overseaprojectinformationsystem.mapper.PotproMapper;
import com.cug.cs.overseaprojectinformationsystem.service.MonthTotalService;
import com.cug.cs.overseaprojectinformationsystem.service.PotproService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.date.DateUtil;

import java.io.Console;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Service
public class MonthTotalServiceImpl implements MonthTotalService {

    @Autowired
    private MonthTotalMapper monthTotalMapper;

    @Autowired
    private NewSignMapper newSignMapper;

    @Autowired
    private PotproMapper potproMapper;

    @Override
    public List<DayRes> listMonthByReq(DayReq dayReq) {
        // 首先根据前端传来的时间区间，查询出区间中所有的月份；
        DateTime startDate =  DateUtil.parse(dayReq.getStartDate(),"yyyy-MM");
        DateTime endDate =  DateUtil.parse(dayReq.getEndDate(),"yyyy-MM");
        List<Date> lDate = findDates(startDate, endDate, Calendar.MONTH);
        if (CollectionUtils.isEmpty(lDate)){
            return null;
        }
        List<DayRes> dayResList = monthTotalMapper.listMonthByReq(dayReq);
        for (Date date : lDate){
            // 当前日期不存在则新增
            String dateStr = DateUtil.format(date, "yyyy/MM");
            // 进行匹配
            if(!dayResList.stream().filter(item -> dateStr.equals(item.getMonthName())).findAny().isPresent()){
                DayRes dayRes = DayRes.builder()
                        .monthName(dateStr)
                        .monthMoney(0D)
                        .build();
                dayResList.add(dayRes);
            }
        }
        List<DayRes> collect = dayResList.stream().sorted(Comparator.comparing(DayRes::getMonthName).reversed()).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<WinRateRes> listWinRateByReq(DayReq dayReq) {
        DateTime startDate =  DateUtil.parse(dayReq.getStartDate(),"yyyy-MM");
        DateTime endDate =  DateUtil.parse(dayReq.getEndDate(),"yyyy-MM");
        List<Date> lDate = findDates(startDate, endDate, Calendar.MONTH);
        if (CollectionUtils.isEmpty(lDate)){
            return null;
        }
        List<WinRateRes> winRateResList = monthTotalMapper.listWinRateByReq(dayReq);
        for (Date date : lDate){
            // 当前日期不存在则新增
            String dateStr = DateUtil.format(date, "yyyy/MM");
            // 进行匹配
            if(!winRateResList.stream().filter(item -> dateStr.equals(item.getMonthName())).findAny().isPresent()){
                WinRateRes winRateRes = WinRateRes.builder()
                        .monthName(dateStr)
                        .winRate("0")
                        .build();
                winRateResList.add(winRateRes);
            }
        }
        List<WinRateRes> collect = winRateResList.stream().sorted(Comparator.comparing(WinRateRes::getMonthName).reversed()).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Date> findDates(Date dBegin, Date dEnd, int rule) {
        List lDate = new ArrayList();
        if (dEnd.before(dBegin)){
            return lDate;
        }
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(rule, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }


    @Override
    public List<String> recentSixMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -5);//在这块修改查询的是前几个月
        String before_six = c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH);//六个月前
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");// 格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(before_six));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        try {
            max.setTime(sdf.parse(sdf.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        List<String> months = result.subList(0, result.size() - 1);
        return months;

    }

    @Override
    public List<Double> recentSixNewSignMoney(List<String> months) {
        List<Double> newSignMoney = new ArrayList<>();
        for (String month : months) {
            String startTime = month+"-01 00:00:00";
            String endTime = month+"-31 23:59:59";
            Double money = newSignMapper.getNewSignMoney(startTime, endTime);
            newSignMoney.add(money);
        }
        return newSignMoney;
    }

    @Override
    public List<Double> potMoney(ArrayList<String> centerList) {
        List<Double> potMoney = new ArrayList<>();
        int n = centerList.size();
        for (String name : centerList) {
            potMoney.add(potproMapper.getPotMoney(name));
        }
        return potMoney;
    }


}
