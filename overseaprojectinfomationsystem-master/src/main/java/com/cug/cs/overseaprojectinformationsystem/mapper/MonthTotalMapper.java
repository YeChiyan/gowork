package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 月度汇总
 */
public interface MonthTotalMapper {

    //领导者驾驶舱的新签市场折线图
    List<DayRes> listMonthByReq(@RequestBody DayReq dayReq);

    //中标率百分比
    List<WinRateRes> listWinRateByReq(@RequestBody DayReq dayReq);



}
