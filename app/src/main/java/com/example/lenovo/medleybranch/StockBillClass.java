package com.example.lenovo.medleybranch;

public class StockBillClass {
    String productIds;
    String productNames;
    String productPrices;
    int totalBill;
    String date;

    public String getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(String productPrices) {
        this.productPrices = productPrices;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public StockBillClass() {
    }

    public StockBillClass(String productIds, String productNames, String prices, int totalBill, String date) {
        this.productIds = productIds;
        this.productNames = productNames;
        this.productPrices = prices;
        this.totalBill = totalBill;
        this.date = date;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }
}
