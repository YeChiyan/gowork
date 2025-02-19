package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@TableName(value = "market_potproject")
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @className: PotProject
 * @author: Zhangz
 * @description: TODO 潜在项目信息
 * @date: 2023/9/15 22:00
 * @version: 1.0
 */
public class PotProject {
    //项目ID
    private Long projectId;
    //用户ID
    private Long userId;
    //海外中心名称
    private String centerName;
    //项目名称
    private String projectName;
    //国家
    private String country;
    //客户姓名
    private String clientName;
    //工作量_千米:非必须
    private Integer workloadKm;
    //工作量_平方千米；非必须
    // 需要对应表的字段

    private Integer workloadSqkm;
    //合同额（万美元）；非必须
    private Double contract;
    //项目类型（选择下拉框）
    private String conType;
    //落实程度
    private String fulfilExtent;
    //重要动态；非必须
    private String impActive;

}
