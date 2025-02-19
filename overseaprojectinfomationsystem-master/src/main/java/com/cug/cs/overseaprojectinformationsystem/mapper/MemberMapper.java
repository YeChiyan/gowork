package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    List<Member> selectByUserNameAndPassword(Member member);
    
    Member selectUserInfoByUserId(@Param("id")Integer  id);
    
    int insertNewUser(Member member);
    
    List<Member> selectUserList();
    
    List<Member> selectUserListByCenterId(@Param("centerId") String centerId);
    
    int updateUserInfo(@Param("member") Member member);
    
    int deletedUserByUserId(@Param("id") Integer id);
}
