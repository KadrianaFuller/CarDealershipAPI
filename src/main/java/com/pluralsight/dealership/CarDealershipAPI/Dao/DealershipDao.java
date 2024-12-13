package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.Dealership;

import java.util.List;

public interface DealershipDao {


    List<Dealership> getAll();
    Dealership getById(int id);
    Dealership insert(Dealership dealership);
    Dealership update(Dealership dealership);
    Dealership delete(int id);
}
