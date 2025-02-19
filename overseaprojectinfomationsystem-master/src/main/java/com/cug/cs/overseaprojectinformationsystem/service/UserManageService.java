package com.cug.cs.overseaprojectinformationsystem.service;


import com.cug.cs.overseaprojectinformationsystem.bean.common.ResponseData;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Member;
import com.cug.cs.overseaprojectinformationsystem.dto.LoginFormDto;

import java.util.List;

public interface UserManageService {
    //用户登录
    //用户注册
    //Jwt判断
    //Userholder判断
    Member selectUserInfoByUserId(Integer id);
    
    boolean createNewUser(Member member);
    
    List<Member> selectUserList();
    
    List<Member> selectUserListByCenterId(String centerId);
    
    boolean updateUserInfo(Member member);
    
    boolean deleteUserByUserId(Integer id);

}
