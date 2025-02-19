package com.cug.cs.overseaprojectinformationsystem.service.impl;

import com.cug.cs.overseaprojectinformationsystem.bean.common.PageBaseInfo;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Bid;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Event;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PageBean;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Presentation;
import com.cug.cs.overseaprojectinformationsystem.mapper.EventMapper;
import com.cug.cs.overseaprojectinformationsystem.mapper.PresentationMapper;
import com.cug.cs.overseaprojectinformationsystem.service.EventService;
import com.cug.cs.overseaprojectinformationsystem.service.PresentationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
@Service
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private PresentationMapper presentationMapper;


    @Override
    public PageBean getAll(PageBaseInfo pageBaseInfo) {
        long count = presentationMapper.count();
        Integer pageNum = pageBaseInfo.getPageNum();
        Integer pageSize = pageBaseInfo.getPageSize();
        if (pageNum != null&&pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Presentation> presentationList = presentationMapper.getAll();
        return new PageBean(presentationList, pageNum, pageSize, count);
    }

    public boolean add(Presentation presentation) {
        return presentationMapper.add(presentation) > 0;
    }


    public boolean update(Presentation presentation) {
        return presentationMapper.update(presentation) > 0;
    }


    public boolean delete(Integer id) {
        return presentationMapper.deleteById(id) > 0;
    }

    @Override
    public List<Integer> getSpeechNum(ArrayList<String> centerName) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String year = new SimpleDateFormat("yyyy").format(date);
        String startTime = year+"-01-01 00:00:00";
        String endTime = year+"-12-31 23:59:59";
        List<Integer> speechNum = new ArrayList<>();
        Integer n = centerName.size();
        for (int i=0;i<n;i++){
            String name = centerName.get(i);
            speechNum.add(presentationMapper.gatSpeechNum(startTime,endTime,name));
        }
        return speechNum;
    }
}