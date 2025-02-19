package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @className: PageBean
 * @author: Zhangz
 * @description: TODO用于潜在项目表的分页查询
 * @date: 2023/9/16 16:32
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private List list;
    private Integer pageNum;
    private Integer pageSize;
    private long total;
}
