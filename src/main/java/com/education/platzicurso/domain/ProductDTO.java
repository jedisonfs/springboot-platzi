package com.education.platzicurso.domain;

public class ProductDTO {

    private int productId;
    private String name;
    private int categoryId;
    private double price;
    private int stock;
    private Boolean active;
    private CategoryDTO categoryDto;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CategoryDTO getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDTO categoryDto) {
        this.categoryDto = categoryDto;
    }

}
