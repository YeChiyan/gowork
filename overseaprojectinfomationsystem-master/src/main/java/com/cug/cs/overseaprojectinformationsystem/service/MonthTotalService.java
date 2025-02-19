package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Transactional
public interface MonthTotalService {

    public List<DayRes> listMonthByReq(DayReq dayReq);

    public List<WinRateRes> listWinRateByReq(DayReq dayReq);

    public List<Date> findDates(Date dBegin, Date dEnd, int rule);

    public List<String> recentSixMonth();

    public List<Double> recentSixNewSignMoney(List<String> months);


    List<Double> potMoney(ArrayList<String> centerList);
}
