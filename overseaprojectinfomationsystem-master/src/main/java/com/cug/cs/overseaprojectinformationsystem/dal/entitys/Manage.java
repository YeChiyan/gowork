package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "contract_manage")
@Data
@ToString
public class Manage {

    private Integer id;

    private String lessor;

    private Integer contractNum;

    private String ourCompany;

    private String ourSignatory;

    private String projectName;

    private Integer workNature;

    private Integer integration;

    private BigDecimal contractAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor == null ? null : lessor.trim();
    }

    public Integer getContractNum() {
        return contractNum;
    }

    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    public String getOurCompany() {
        return ourCompany;
    }

    public void setOurCompany(String ourCompany) {
        this.ourCompany = ourCompany == null ? null : ourCompany.trim();
    }

    public String getOurSignatory() {
        return ourSignatory;
    }

    public void setOurSignatory(String ourSignatory) {
        this.ourSignatory = ourSignatory == null ? null : ourSignatory.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getWorkNature() {
        return workNature;
    }

    public void setWorkNature(Integer workNature) {
        this.workNature = workNature;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}