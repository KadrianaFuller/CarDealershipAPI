package com.pluralsight.dealership.CarDealershipAPI.Model;

public class SalesContract extends Contract {

    private double saleTaxAmount =0.05;
    private double recordingFee = 100.00;
    private double processingFee;
    private boolean wantToFinance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, double saleTaxAmount, double recordingFee, double processingFee, boolean wantToFinance) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.saleTaxAmount = saleTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.wantToFinance = wantToFinance;
    }

    public double getSaleTaxAmount() {
        return saleTaxAmount;
    }

    public void setSaleTaxAmount(double saleTaxAmount) {
        this.saleTaxAmount = saleTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public double setProcessingFee(double processingFee) {
        if (getTotalPrice() <=100000){
            return 295;
        } else {
            return 495;
        }
    }

    public boolean isWantToFinance() {
        return wantToFinance;
    }

    public void setWantToFinance(boolean wantToFinance) {
        this.wantToFinance = wantToFinance;
    }

    @Override
    public double getTotalPrice() {
        return saleTaxAmount + processingFee + recordingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!wantToFinance){
            return 0;
        }
        double totalPrice = getTotalPrice();
        double interestRate;
        int loanTerm;

        if(totalPrice >= 10000){
            interestRate = 0.0425 ;
            loanTerm = 48;
        }else {
            interestRate =0.0525 ;
            loanTerm = 24;
        }
        double monthlyInterestRate = interestRate /12;

        double monthlyPayment = (totalPrice * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, - loanTerm));

        return monthlyPayment;



    }
}
