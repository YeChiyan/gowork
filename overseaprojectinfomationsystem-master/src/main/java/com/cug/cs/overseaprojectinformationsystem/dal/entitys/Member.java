package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.cug.cs.overseaprojectinformationsystem.typehandler.IntegerArrayTypeHandler;
import com.cug.cs.overseaprojectinformationsystem.typehandler.StringArrayTypeHandler;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;
import com.baomidou.mybatisplus.annotation.TableName;


import java.util.Date;


@Data
@ToString
@TableName("member")
public class Member {
   
    private Long id;
    
    private String name;
    /**
     * 账号
     */
    private String username;
    
    /**
     * 密码，加密存储
     */
    private String password;
    
    private int sex;
    
    private String phone;
    private String email;
    private String department;
    private String centerId;
    
    @TableField(jdbcType = JdbcType.VARCHAR,value ="role_id" ,typeHandler = IntegerArrayTypeHandler.class)
    private Integer[] roleId;
    
    private boolean deleted;
    
}