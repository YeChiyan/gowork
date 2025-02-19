package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 接收新签市场返回金额的pojo
 */
@Table(name = "market_newsign")
@Data
@ToString
public class TotalAmount {
    @Column(name = "contract_amount")
    private Double totalAmount;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
