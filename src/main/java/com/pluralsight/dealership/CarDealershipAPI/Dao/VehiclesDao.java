package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.Vehicle;

import java.util.List;

public interface VehiclesDao {

    List<Vehicle> getAll();
    Vehicle getById(int id);
    Vehicle add(Vehicle vehicle);
    Vehicle update(Vehicle vehicle);
    Vehicle delete(int id);
}
