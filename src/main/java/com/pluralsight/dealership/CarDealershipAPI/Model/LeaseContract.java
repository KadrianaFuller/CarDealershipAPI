package com.pluralsight.dealership.CarDealershipAPI.Model;

public class LeaseContract extends Contract {

    private double endingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, double endingValue, double leaseFee) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.endingValue = endingValue;
        this.leaseFee = leaseFee;
    }

    public double getEndingValue() {

        return endingValue;
    }

    public void setEndingValue(double endingValue) {

        this.endingValue = endingValue;
    }

    public double getLeaseFee() {

        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {

        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double originalPrice = getVehicleSold().getPrice();
        endingValue = originalPrice *0.5;
        leaseFee = originalPrice *0.07;
        return endingValue + leaseFee;

    }

    @Override
    public double getMonthlyPayment() {

        double totalPrice = getTotalPrice();
        double interestRate = 0.04/12;
        int loanLength = 36;

        double monthlyPayment = (totalPrice * interestRate) / (1 - Math.pow(1 + interestRate, -loanLength));

        return monthlyPayment;

    }
}

