package com.example.lenovo.medleybranch;

public class Customer_Bill {
    String billID;
    String CarID;
    String billDate;
    String nextDate;
    String meterReading;

    public Customer_Bill() {
    }

    public Customer_Bill(String billID, String carID, String billDate, String nextDate, String meterReading) {
        this.billID = billID;
        CarID = carID;
        this.billDate = billDate;
        this.nextDate = nextDate;
        this.meterReading = meterReading;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String carID) {
        CarID = carID;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public String getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }
}