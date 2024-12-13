package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.SalesContract;

import java.util.List;

public interface SalesContractDao {

    List<SalesContract> getAll();
    SalesContract getById(int id);
    SalesContract add(SalesContract salesContract);
    SalesContract update(SalesContract salesContract);
    SalesContract delete(int id);

}
