package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author shentong
 * @date 2023/11/8 16:07
 * @description
 */

@TableName(value = "market_newsign")
@Data
@ToString
public class NewSignTotal {

    @Column(name = "project_name")
    private String projectName;

    private String lessor;

    @Column(name = "contract_amount")
    private BigDecimal contractAmount;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }
}
