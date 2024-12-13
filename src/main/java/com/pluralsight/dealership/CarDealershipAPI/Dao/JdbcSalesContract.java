package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.SalesContract;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSalesContract implements SalesContractDao{

    private DataSource dataSource;
    private List<SalesContract> salesContracts;




    @Override
    public List<SalesContract> getAll() {
        this.salesContracts.clear();
        return List.of();
    }

    @Override
    public SalesContract getById(int id) {
        return null;
    }

    @Override
    public SalesContract add(SalesContract salesContract) {

        return null;
    }

    @Override
    public SalesContract update(SalesContract salesContract) {
        return null;
    }

    @Override
    public SalesContract delete(int id) {
        return null;
    }
}
