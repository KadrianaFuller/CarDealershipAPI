package com.pluralsight.dealership.CarDealershipAPI.Model;

public abstract class Contract {
    private String date, customerName, customerEmail;
    protected Vehicle vehicleSold;
    private double totalPrice, monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Vehicle getVehicleSold() {

        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {

        this.vehicleSold = vehicleSold;
    }
}
