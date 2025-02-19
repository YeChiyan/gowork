package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author shentong
 * @date 2023/11/9 10:30
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayReq {
    /**
     * 开始日期
     */
    @NotBlank(message = "起始日期不能为空！")
    private String startDate;

    /**
     * 结束日期
     */
    @NotBlank(message = "截止日期不能为空！")
    private String endDate;

}
