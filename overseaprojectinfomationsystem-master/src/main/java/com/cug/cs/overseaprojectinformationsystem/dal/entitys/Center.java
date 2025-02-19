package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import lombok.Data;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-10-06  17:12
 */
@Data
public class Center {
    private Integer id;
    
    private String centerName;
    private String address;
    private String leader;
    private String department;
    private Integer[] roleId;
    private Boolean deleted;
}
