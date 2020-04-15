package com.example.lenovo.medleybranch;

public class ProductClass {
    private String productId;
    private String productNumber;
    private String categoryId;
    private String productName;
    private int purchasePrice;
    private int salePrice;
    private String productDescription;
    private String companyName;
    private String entryDate;

    public ProductClass() {
    }

    public ProductClass(String productId, String productNumber,String categoryId, String productName, int purchasePrice, int salePrice, String productDescription, String companyName, String entryDate) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productNumber  = productNumber;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.productDescription = productDescription;
        this.companyName = companyName;
        this.entryDate = entryDate;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    @Override
    public String toString() {
        return "ProductClass{" +
                "categoryId='" + categoryId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", productDescription='" + productDescription + '\'' +
                ", companyName='" + companyName + '\'' +
                ", entryDate='" + entryDate + '\'' +
                '}';
    }


}
