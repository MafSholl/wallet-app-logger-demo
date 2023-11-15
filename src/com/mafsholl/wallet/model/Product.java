package com.mafsholl.wallet.model;

public class Product {

    private Integer id;
    private String name;
    private String description;
    private Double unitPrice;
    private Integer quantityInStock;
    private String dateCreated;
    private Boolean available;

    public Product(String name, String description, Double unitPrice, Integer quantityInStock) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantityInStock = quantityInStock;
    }

    public Product(Integer id, String name, String description, Double unitPrice,
                   Integer quantityInStock, String dateCreated, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantityInStock = quantityInStock;
        this.dateCreated = dateCreated;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantityInStock=" + quantityInStock +
                ", dateCreated='" + dateCreated + '\'' +
                ", available=" + available +
                '}';
    }
}
