package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 投标统计pojo
 */
//@Table(name = "market_bidstatistics")
@TableName(value = "market_bidstatistics")
@Data
@ToString
public class Bid {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String area;

    private String country;

    private String lessor;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "invite_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inviteTime;

    @Column(name = "close_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date closeTime;

    @Column(name = "busidate_type")
    private String busidateType;

    @Column(name = "tendet_body")
    private String tenderBody;

    private Integer support;

    @Column(name = "tender_method")
    private Integer tenderMethod;

    @Column(name = "eva_dynamics")
    private String evaDynamics;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String reason;

    private BigDecimal amount;

    private BigDecimal workload;

    private BigDecimal univalent;

    private BigDecimal prive;

    private Integer integration;

    private String note;

    public Integer getSupport() {
        return support;
    }

    public void setSupport(Integer support) {
        this.support = support;
    }

    public Integer getTenderMethod() {
        return tenderMethod;
    }

    public void setTenderMethod(Integer tenderMethod) {
        this.tenderMethod = tenderMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getBusidateType() {
        return busidateType;
    }

    public void setBusidateType(String busidateType) {
        this.busidateType = busidateType;
    }

    public String getTenderBody() {
        return tenderBody;
    }

    public void setTenderBody(String tenderBody) {
        this.tenderBody = tenderBody;
    }

    public String getEvaDynamics() {
        return evaDynamics;
    }

    public void setEvaDynamics(String evaDynamics) {
        this.evaDynamics = evaDynamics;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getWorkload() {
        return workload;
    }

    public void setWorkload(BigDecimal workload) {
        this.workload = workload;
    }

    public BigDecimal getUnivalent() {
        return univalent;
    }

    public void setUnivalent(BigDecimal univalent) {
        this.univalent = univalent;
    }

    public BigDecimal getPrive() {
        return prive;
    }

    public void setPrive(BigDecimal prive) {
        this.prive = prive;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
