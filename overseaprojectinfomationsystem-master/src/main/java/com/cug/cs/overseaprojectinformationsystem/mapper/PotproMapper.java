package com.cug.cs.overseaprojectinformationsystem.mapper;

import com.cug.cs.overseaprojectinformationsystem.dal.entitys.PotProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: PotproMapper
 * @author: Zhangz
 * @description: TODO
 * @date: 2023/9/15 22:25
 * @version: 1.0
 */
@Mapper
public interface PotproMapper {
    public void addPotproject(PotProject potProject);

    /*
    下两步操作进行分页查询
     */
    public long count();

    public List<PotProject> selectPotproject();

    //删除员工
    public void deletePotproject(List<Integer> projectIds);
    public void deletePotproject1(PotProject potProject);
    int deleteByPrimaryKey(Integer projectId);

    //更新项目
    public void updatePotproject(PotProject potProject);

    Integer getPotNum(String name);


    Double getPotMoney(String name);
}
