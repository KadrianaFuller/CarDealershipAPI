package com.pluralsight.dealership.CarDealershipAPI.FileManager;

import com.pluralsight.dealership.CarDealershipAPI.Model.Dealership;
import com.pluralsight.dealership.CarDealershipAPI.Model.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DealershipFileManager {

    public static Dealership getDealership() {
        Dealership dealership = new Dealership("TK Auto", "111 Old Benbrook Rd", "817-555-5555");
        String filePath = "src/main/resources/inventory.csv";
        //  try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
//            String input;
//            while ((input = bufferedReader.readLine()) != null) {
//                if (input.startsWith("T")) {
//                    continue;
//                }
//
//                String[] tokens = input.split("\\|");
//                String vin = tokens[0];
//                int year = Integer.parseInt(tokens[1]);
//                String make = tokens[2];
//                String model = tokens[3];
//                String vehicleType = tokens[4];
//                String color = tokens[5];
//                int odometer = Integer.parseInt(tokens[6]);
//                double price = Double.parseDouble(tokens[7]);
//
//                //populate inventory with list of Vehicles
//                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
//                dealership.addVehicle(vehicle);
//            }
        return dealership;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void saveDealership(Dealership dealership) {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/inventory.csv"));

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                //Format inventory entry and write to file
                String inventoryEntry = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",vehicle.getVin(),vehicle.getYear(),vehicle.getMake(),vehicle.getModel(),vehicle.getVehicleType(),vehicle.getColor(), vehicle.getOdometer(),vehicle.getPrice());
                bufWriter.write(inventoryEntry);
            }

            //Release file
            bufWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}