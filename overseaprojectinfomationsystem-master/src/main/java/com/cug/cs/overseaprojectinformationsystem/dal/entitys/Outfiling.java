package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "contract_outfiling")
@Data
@ToString
public class Outfiling {
    private Integer id;

    private String overseaCenter;

    private String counterpartyUnit;

    private String ourUnit;

    private String contractName;

    private Integer contractType;

    private BigDecimal contractAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signingTime;

    private Integer approvalStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverseaCenter() {
        return overseaCenter;
    }

    public void setOverseaCenter(String overseaCenter) {
        this.overseaCenter = overseaCenter == null ? null : overseaCenter.trim();
    }

    public String getCounterpartyUnit() {
        return counterpartyUnit;
    }

    public void setCounterpartyUnit(String counterpartyUnit) {
        this.counterpartyUnit = counterpartyUnit == null ? null : counterpartyUnit.trim();
    }

    public String getOurUnit() {
        return ourUnit;
    }

    public void setOurUnit(String ourUnit) {
        this.ourUnit = ourUnit == null ? null : ourUnit.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Date getSigningTime() {
        return signingTime;
    }

    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}