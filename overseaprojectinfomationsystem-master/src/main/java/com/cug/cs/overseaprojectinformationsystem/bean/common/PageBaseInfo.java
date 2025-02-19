package com.cug.cs.overseaprojectinformationsystem.bean.common;

import lombok.Data;

/**
 * @description: TODO 该类主要是用于分页查询，显示给前端
 * @author: ShengHui
 * @date: 2023-09-01  23:36
 */
@Data
public class PageBaseInfo {
    Integer pageNum;
    Integer pageSize;
    String key;
    String sort;
    String order;
}
