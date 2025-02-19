package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.bo.CenterListVO;
import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Center;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Member;
import com.cug.cs.overseaprojectinformationsystem.mapper.CenterMapper;
import com.cug.cs.overseaprojectinformationsystem.mapper.MemberMapper;
import com.cug.cs.overseaprojectinformationsystem.service.CenterManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-17  23:31
 */
@Service
public class CenterManageServiceImpl implements CenterManageService {
    @Autowired
    CenterMapper centerMapper;
    @Autowired
    MemberMapper memberMapper;
    
    @Transactional
    @Override
    public boolean insertNewCenter(Center center) {
        int row=centerMapper.insertNewCenter(center);
        if (row == 1) {
            return true;
        }
        return false;
    }
    
    @Transactional
    @Override
    public boolean deleteCenterInfo(Integer id) {
        int row = centerMapper.deleteCenterByCenterId(id);
        List<Member> memberList = memberMapper.selectUserListByCenterId(Integer.toString(id));
        int count=0;
        for (Member member : memberList) {
            int col = memberMapper.deletedUserByUserId(Integer.parseInt(member.getId().toString()));
            if (col == 1) {
                count++;
            }
        }
        if (row == 1 && count == memberList.size()) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public List<CenterListVO> selectCenterInfo() {
        List<Center> centerList = centerMapper.selectCenterList();
        List<CenterListVO> centerListVOS = new ArrayList<>();
        for (Center center : centerList) {
            CenterListVO centerListVO = new CenterListVO();
            centerListVO.setId(center.getId());
            centerListVO.setCenterName(center.getCenterName());
            centerListVO.setDeleted(center.getDeleted());
            centerListVOS.add(centerListVO);
        }
        return centerListVOS;
    }
    
    @Transactional
    @Override
    public boolean updateCenterInfo(Center center) {
        int row = centerMapper.updateCenterInfo(center);
        if (row == 1) {
            return true;
        }
        return false;
    }
    
    @Override
    public Center selectCenterInfoByCenterId(Integer id) {
        Center centerInfo=centerMapper.selectCenterInfoByCenterId(id);
        return centerInfo;
    }
    
    @Override
    public PageInfo selectCenterList(PageBaseInfo pageBaseInfo) {
        PageHelper.startPage(pageBaseInfo.getPageNum(), pageBaseInfo.getPageSize());
        List<Center> centerList=centerMapper.selectCenterList();
        // PageInfo<Center> centerPageInfo = new PageInfo<>(centerList);
        return new PageInfo<>(centerList);
    }
    
    @Override
    public boolean selectCenterExistsByCenterId(String centerId) {
        Integer result=centerMapper.selectCenterByCenterId(centerId);
        if (result == 1) {
            return true;
        }
        return false;
    }
}
