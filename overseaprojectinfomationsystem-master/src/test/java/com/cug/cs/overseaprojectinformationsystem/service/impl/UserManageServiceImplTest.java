package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.dto.Result;
import com.cug.cs.overseaprojectinformationsystem.service.UserManageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserManageServiceImplTest {
    
    @Autowired
    UserManageService userManageService;
    @Test
    void sendCode() {
        Result result = userManageService.sendCode("19912341234");
        System.out.println("help");
        System.out.println(result);
    }
}