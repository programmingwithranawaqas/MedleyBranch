package com.example.lenovo.medleybranch;

public class StockClass {
    int productId;
    String categoryId;
    int productQuantity;
    String lastUpdateDate;
    int productLeftQuantity;

    public int getProductLeftQuantity() {
        return productLeftQuantity;
    }

    public void setProductLeftQuantity(int productLeftQuantity) {
        this.productLeftQuantity = productLeftQuantity;
    }

    public StockClass() {
    }

    public StockClass(int productId, String categoryId, int productQuantity, String lastUpdateDate) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productQuantity = productQuantity;
        this.lastUpdateDate = lastUpdateDate;
        this.productLeftQuantity = productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "StockClass{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", productQuantity=" + productQuantity +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                '}';
    }
}
