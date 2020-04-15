package com.example.lenovo.medleybranch;

public class CustomerProductsList {
    String billID;
    String productsID;

    public CustomerProductsList() {
    }

    public CustomerProductsList(String billID, String productsID) {
        this.billID = billID;
        this.productsID = productsID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getProductsID() {
        return productsID;
    }

    public void setProductsID(String productsID) {
        this.productsID = productsID;
    }
}
