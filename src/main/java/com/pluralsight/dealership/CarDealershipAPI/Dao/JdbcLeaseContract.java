package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.LeaseContract;

import java.util.List;

public class JdbcLeaseContract implements LeaseContractDao{
    @Override
    public List<LeaseContract> getAll() {
        return List.of();
    }

    @Override
    public LeaseContract getById(int id) {
        return null;
    }

    @Override
    public LeaseContract add(LeaseContract contract) {
        return null;
    }

    @Override
    public LeaseContract update(LeaseContract contract) {
        return null;
    }

    @Override
    public LeaseContract delete(int id) {
        return null;
    }
}
