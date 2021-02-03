package com.domork.demo.enpoint.dto;

import java.math.BigDecimal;
import java.util.Currency;

public class ProductDto {
    private Long ID;
    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    //private Currency price;
    private int quantity;
    private String condition;

    public ProductDto() {
    }

    public ProductDto(Long ID, String name, String category, String description, BigDecimal price, int quantity, String condition) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.condition = condition;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", condition='" + condition + '\'' +
                '}';
    }
}
