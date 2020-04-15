package com.example.lenovo.medleybranch;

public class CustomerClass {
    String customerName;
    String customerEmail;
    String customerMobileNumber;
    int customerBalance;
    String carRegistrationNumber;
    String carBrand;
    String carModel;
    float carMileage;
    String carLocation;
    String cnic;
    public CustomerClass() {
    }

    public CustomerClass(String customerName, String customerEmail, String customerMobileNumber, int customerBalance, String carRegistrationNumber, String carBrand, String carModel, float carMileage, String carLocation, String cnic) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerMobileNumber = customerMobileNumber;
        this.customerBalance = customerBalance;
        this.carRegistrationNumber = carRegistrationNumber;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carMileage = carMileage;
        this.carLocation = carLocation;
        this.cnic = cnic;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public int getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(int customerBalance) {
        this.customerBalance = customerBalance;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public float getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(float carMileage) {
        this.carMileage = carMileage;
    }

    public String getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(String carLocation) {
        this.carLocation = carLocation;
    }
}
