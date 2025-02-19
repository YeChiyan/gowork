package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 新签市场一览表返回的实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayRes {

    /**
     * 月份
     */
    private String monthName;

    /**
     * 金额
     */
    private Double monthMoney;


}
