package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketConditions {
    private Integer id;

    private String overseaCenter;

    private Integer annualMetrics;

    private Integer timeSchedule;

    private BigDecimal done;

    private Integer lastYear;

}