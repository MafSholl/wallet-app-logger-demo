package com.mafsholl.wallet.dto;

import java.util.Objects;

public class Cart {

    private Integer productId;

    private String productName;

    private String description;

    private Double unitPrice;

    private Integer quantity;

    private Double subTotal;

    public Cart(Integer productId, String productName, String description, Double unitPrice, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subTotal = calSubTotal();
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.subTotal = calSubTotal();
    }

    private Double calSubTotal() {
        return this.unitPrice * this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return productId.equals(cart.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }
}
