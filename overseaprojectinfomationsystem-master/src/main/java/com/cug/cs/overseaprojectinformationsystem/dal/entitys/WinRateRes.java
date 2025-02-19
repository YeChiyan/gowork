package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author shentong
 * @date 2023/11/10 11:11
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WinRateRes {

    /**
     * 月份
     */
    private String monthName;

    /**
     * 中标率
     */
    private String winRate;

}
