package com.pluralsight.dealership.CarDealershipAPI.Interface;

import com.pluralsight.dealership.CarDealershipAPI.FileManager.DealershipFileManager;
import com.pluralsight.dealership.CarDealershipAPI.Model.LeaseContract;
import com.pluralsight.dealership.CarDealershipAPI.Model.SalesContract;
import com.pluralsight.dealership.CarDealershipAPI.Model.Dealership;
import com.pluralsight.dealership.CarDealershipAPI.Model.Vehicle;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public Dealership dealership;
    public static Scanner scan = new Scanner(System.in);
    private List<Vehicle> vehicles;

    private void init() {
        dealership = DealershipFileManager.getDealership();

    }

    public void display() {
        init();


        while (true) {
            displayMenu();
            String userOptions = scan.nextLine();

            switch (userOptions) {

                case "1":
                    viewAllVehicles();
                    break;
                case "2":
                    searchCriteria();
                    break;
                case "3":
                    saleOrLease();
                    break;
                case "4":
                    System.out.println("Thank you for choosing TK Auto. We hope to see you soon! Goodbye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option chosen, try again");
            }

        }

    }

    private void saleOrLease() {
        System.out.println("Please enter your name:");
        String buyerName = scan.nextLine();

        System.out.println("Please enter your email:");
        String buyerEmail = scan.nextLine();

        System.out.println("Enter the VIN of the vehicle:");
        String vin = scan.nextLine();

        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle not found. Please try again.");
            return;
        }

        System.out.println("""
        What would you like to do with this vehicle?
        1. Sale
        2. Lease
        Enter your choice (1 or 2):""");

        String choice = scan.nextLine();

        switch (choice) {
            case "1": // Sale
                processSaleContract(buyerName, buyerEmail, vehicle);
                break;

            case "2": // Lease
                if (vehicle.getYear() < (2024 - 3)) {
                    System.out.println("Vehicles older than 3 years cannot be leased.");
                    return;
                }
                processLeaseContract(buyerName, buyerEmail, vehicle);
                break;

            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }

    private void processLeaseContract(String buyerName, String buyerEmail, Vehicle vehicle) {
        double vehiclePrice = vehicle.getPrice();

        // Calculate lease details
        double endingValue = vehiclePrice * 0.50; // 50% of original price
        double leaseFee = vehiclePrice * 0.07; // 7% of original price
        double totalPrice = endingValue + leaseFee; // Total lease cost

        // Calculate monthly payment for 36-month lease at 4% annual interest
        double monthlyInterestRate = 0.04 / 12;
        int leaseTerm = 36;
        double monthlyPayment = (totalPrice * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -leaseTerm));

        // Create the lease contract
        LeaseContract leaseContract = new LeaseContract(
                java.time.LocalDate.now().toString(), // Current date
                buyerName,
                buyerEmail,
                vehicle,
                totalPrice,
                monthlyPayment,
                endingValue,
                leaseFee
        );

        // Save the contract and remove the vehicle from inventory
        dealership.addContract(leaseContract);
        dealership.removeVehicle(vehicle.getVin());

        // Confirmation message
        System.out.println("Lease contract created successfully:");
        System.out.println(leaseContract);
        System.out.println("Vehicle has been removed from the inventory.");

    }

    private void processSaleContract(String buyerName, String buyerEmail, Vehicle vehicle) {
        double vehiclePrice = vehicle.getPrice();
        double saleTax = vehiclePrice * 0.05; // 5% sales tax
        double recordingFee = 100; // Fixed fee
        double processingFee = vehiclePrice < 10000 ? 295 : 495; // Based on price
        double totalPrice = vehiclePrice + saleTax + recordingFee + processingFee;

        System.out.println("Do they want to finance the purchase? (yes/no):");
        boolean wantToFinance = "yes".equalsIgnoreCase(scan.nextLine());

        double monthlyPayment = 0;
        if (wantToFinance) {
            double interestRate = vehiclePrice >= 10000 ? 0.0425 : 0.0525; // 4.25% or 5.25%
            int loanTerm = vehiclePrice >= 10000 ? 48 : 24; // 48 or 24 months
            double monthlyInterestRate = interestRate / 12;

            monthlyPayment = (totalPrice * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
        }

        SalesContract salesContract = new SalesContract(
                java.time.LocalDate.now().toString(),
                buyerName,
                buyerEmail,
                vehicle,
                totalPrice,
                monthlyPayment,
                saleTax,
                recordingFee,
                processingFee,
                wantToFinance
        );

        dealership.addContract(salesContract);
        System.out.println("Sales contract created successfully:");
        System.out.println(salesContract);

        dealership.removeVehicle(vehicle.getVin());
        System.out.println("Vehicle has been removed from the inventory.");
    }
    public void processAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicles); // Display each vehicle using the helper method
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

    }

    public void viewAllVehicles() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processGetByPriceRequest() {
        System.out.println("Enter minimum price:");
        double minPrice = scan.nextDouble();
        System.out.println("Enter maximum price:");
        double maxPrice = scan.nextDouble();
        scan.nextLine(); // Consume newline

        List<Vehicle> vehiclesByPrice = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehiclesByPrice);
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Enter vehicle make:");
        String make = scan.nextLine();
        System.out.println("Enter vehicle model:");
        String model = scan.nextLine();

        List<Vehicle> vehiclesByMakeModel = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehiclesByMakeModel);
    }

    public void processGetByYearRequest() {
        System.out.println("Enter the year of the vehicle:");
        int year = scan.nextInt();
        scan.nextLine(); // Consume newline

        List<Vehicle> vehiclesByYear = dealership.getVehiclesByYear(year);
        displayVehicles(vehiclesByYear);
    }

    public void processGetByColorRequest() {
        System.out.println("Enter the color of the vehicle:");
        String color = scan.nextLine();

        List<Vehicle> vehiclesByColor = dealership.getVehiclesByColor(color);
        displayVehicles(vehiclesByColor);
    }

    public void processGetByMileageRequest() {
        System.out.println("Enter maximum mileage:");
        int maxMileage = scan.nextInt();
        scan.nextLine(); // Consume newline

        List<Vehicle> vehiclesByMileage = dealership.getVehiclesByMileage(maxMileage);
        displayVehicles(vehiclesByMileage);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter the type of vehicle (e.g., Sedan, SUV, etc.):");
        String vehicleType = scan.nextLine();

        List<Vehicle> vehiclesByType = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehiclesByType);
    }

    public void processGetAllVehicleRequest() {
        List<Vehicle> allVehicles= dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest() {
        System.out.println("Enter the VIN number:");
        String vin = scan.nextLine();
        System.out.println("Enter the year:");
        int year = scan.nextInt();
        scan.nextLine(); // Consume newline
        System.out.println("Enter the make:");
        String make = scan.nextLine();
        System.out.println("Enter the model:");
        String model = scan.nextLine();
        System.out.println("Enter the type (e.g., Sedan, SUV):");
        String vehicleType = scan.nextLine();
        System.out.println("Enter the color:");
        String color = scan.nextLine();
        System.out.println("Enter the odometer reading:");
        int odometer = scan.nextInt();
        System.out.println("Enter the price:");
        double price = scan.nextDouble();
        scan.nextLine(); // Consume newline

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);
        System.out.println("Vehicle added successfully!");

    }

    public void processRemoveVehicleRequest() {
        System.out.println("Please enter the Vin of the vehicle you would like to remove: ");
        String vehicleVin = scan.nextLine();
        boolean vehicleMatch = false;

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicleVin == vehicle.getVin()) {
                vehicleMatch = true;
                dealership.removeVehicle(vehicleVin);
                System.out.println("Vehicle has been removed!");
                DealershipFileManager.saveDealership(dealership);
                break;
            }
        }

    }

    private void searchCriteria() {
        System.out.println("""
                1.Search by Price
                2.Search by Make/Model
                3.Search by Year
                4.Search by Color
                5.Search by Mileage
                6.Search by Vehicle Type\n""");
        int searchOption = scan.nextInt();
        switch (searchOption) {
            case 1 -> processGetByPriceRequest();
            case 2 -> processGetByMakeModelRequest();
            case 3 -> processGetByYearRequest();
            case 4 -> processGetByColorRequest();
            case 5 -> processGetByMileageRequest();
            case 6 -> processGetByVehicleTypeRequest();
        }
    }

    public void displayMenu() {
        System.out.println("""
                Welcome to TK Auto!
                1.View All Vehicles
                2.Search by Criteria:
                3.Sell/Lease
                4.Exit""");

    }
}


