package com.cug.cs.overseaprojectinformationsystem.mapper;


import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Center;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CenterMapper {
    Integer selectCenterByCenterId(@Param("id") String centerId);
    
    List<Center> selectCenterList();
    
    int insertNewCenter(@Param("center") Center center);
    
    Center selectCenterInfoByCenterId(@Param("id") Integer id);
    
    int updateCenterInfo(@Param("center") Center center);
    
    int deleteCenterByCenterId(@Param("id") Integer id);
}
