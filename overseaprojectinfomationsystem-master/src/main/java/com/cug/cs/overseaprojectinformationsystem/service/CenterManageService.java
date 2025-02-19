package com.cug.cs.overseaprojectinformationsystem.service;

import com.cug.cs.overseaprojectinformationsystem.bean.bo.CenterListVO;
import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Center;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CenterManageService {
    boolean selectCenterExistsByCenterId(String centerId);
    
    PageInfo selectCenterList(PageBaseInfo pageBaseInfo);
    
    boolean insertNewCenter(Center center);
    
    Center selectCenterInfoByCenterId(Integer id);
    
    boolean updateCenterInfo(Center center);
    
    boolean deleteCenterInfo(Integer id);
    
    List<CenterListVO> selectCenterInfo();
    
}
