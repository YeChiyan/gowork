package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.NewSign;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.NewSignTotal;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 新签市场
 */
public interface NewSignMapper {
    Double getNewSignMoney(String startTime,String endTime);

    /**
     * 查询所有
     * @return
     */
    List<NewSign> getAll();;

    /**
     * 按中心查询
     */
    List<NewSign> selectByCenter(String name);

    long countByCenter(String name);

    /**
     * 添加
     */
    int add(NewSign newSign);

    /**
     * 修改
     */
    int update(NewSign newSign);

    /**
     * 删除
     */
    int deleteById(int id);

    long count();

//    获得开发的区域范围（km+sqkm）
    BigDecimal get2d(String contractNum);
    BigDecimal get3d(String contractNum);

}
