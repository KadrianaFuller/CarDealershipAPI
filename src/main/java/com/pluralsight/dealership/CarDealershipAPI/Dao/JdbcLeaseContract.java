package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class JdbcLeaseContract implements LeaseContractDao{
    private DataSource dataSource;
    private List<LeaseContract> leaseContracts;


    @Autowired
    public JdbcLeaseContract(DataSource dataSource){
        this.dataSource = dataSource;
        this.leaseContracts = new ArrayList<>();
    }





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

    @Override
    public LeaseContract insert(LeaseContract leaseContract) {
        return null;
    }
}
