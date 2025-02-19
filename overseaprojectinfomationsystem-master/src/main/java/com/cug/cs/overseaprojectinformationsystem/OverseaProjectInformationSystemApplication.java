package com.cug.cs.overseaprojectinformationsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: TODO 启动类
 * @author: ShengHui
 * @date: 2023-07-18  21:44
 */
@SpringBootApplication
@MapperScan({"com.cug.cs.overseaprojectinformationsystem.mapper","com.cug.cs.overseaprojectinformationsystem.dao"})
public class OverseaProjectInformationSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(OverseaProjectInformationSystemApplication.class,args);
        
    }
}
