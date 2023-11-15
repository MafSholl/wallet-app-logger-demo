package com.mafsholl.wallet.dto;

import com.mafsholl.wallet.Utility;

import java.sql.Date;
import java.time.LocalDate;

public class CustomerProduct {

    private Integer customerId;

    private Integer productId;

    private Double unitPrice;

    private Integer quantity;

    private String orderCode;

    private Date createdDate;

    public CustomerProduct(Integer customerId, Integer productId, Double unitPrice, Integer quantity) {
        this.orderCode = Utility.generateOrderCode();
        this.createdDate = Date.valueOf(LocalDate.now());
        this.customerId = customerId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
