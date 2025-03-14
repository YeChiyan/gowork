package com.contractdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.contractdemo.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    @Select("SELECT * FROM member ORDER BY id ASC LIMIT #{offset}, #{pageSize}")
    List<Member> selectPageWithCustomLimit(@Param("offset") long offset, @Param("pageSize") long pageSize);

    @Select("SELECT COUNT(*) FROM member")
    Long selectTotalCount();
}
