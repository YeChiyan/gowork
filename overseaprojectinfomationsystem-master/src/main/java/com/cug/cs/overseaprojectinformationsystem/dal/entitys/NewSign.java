package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 新签市场pojo
 */
//@Table(name = "market_newsign")
@TableName(value = "market_newsign")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewSign {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String area;

    private String country;

    @Column(name = "workarea_name")
    private String workareaName;

    private String lessor;

    @Column(name = "our_company")
    private String ourCompany;

    @Column(name = "signatory_name")
    private String signatoryName;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "contract_name")
    private String contractNum;

    @Column(name = "work_nature")
    private String workNature;

    private Integer integration;

    @Column(name = "surface_type")
    private String surfaceType;

    private Integer pstm;

    private String psdm;
    private BigDecimal workload_2d;
    private BigDecimal workload_3d;

    @Column(name = "contract_amount")
    private BigDecimal contractAmount;

    @Column(name = "sign_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signDate;

    @Column(name = "close_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date closeDate;

    private String note;

    public String getWorkareaName() {
        return workareaName;
    }

    public void setWorkareaName(String workAreaName) {
        this.workareaName = workAreaName;
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

    public String getOurCompany() {
        return ourCompany;
    }

    public void setOurCompany(String ourCompany) {
        this.ourCompany = ourCompany;
    }

    public String getSignatoryName() {
        return signatoryName;
    }

    public void setSignatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getWorkNature() {
        return workNature;
    }

    public void setWorkNature(String workNature) {
        this.workNature = workNature;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

    public Integer getPstm() {
        return pstm;
    }

    public void setPstm(Integer pstm) {
        this.pstm = pstm;
    }

    public String getPsdm() {
        return psdm;
    }

    public void setPsdm(String psdm) {
        this.psdm = psdm;
    }

    public BigDecimal getWorkload_2d() {
        return workload_2d;
    }

    public void setWorkload_2d(BigDecimal workload_2d) {
        this.workload_2d = workload_2d;
    }

    public BigDecimal getWorkload_3d() {
        return workload_3d;
    }

    public void setWorkload_3d(BigDecimal workload_3d) {
        this.workload_3d = workload_3d;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
