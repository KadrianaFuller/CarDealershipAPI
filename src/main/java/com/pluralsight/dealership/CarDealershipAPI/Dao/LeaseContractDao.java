package com.pluralsight.dealership.CarDealershipAPI.Dao;



import com.pluralsight.dealership.CarDealershipAPI.Model.LeaseContract;

import java.util.List;

public interface LeaseContractDao {

    List<LeaseContract> getAll();
    LeaseContract getById(int id);
    LeaseContract  add(LeaseContract contract);
    LeaseContract update(LeaseContract contract);
    LeaseContract delete(int id);

    LeaseContract insert(LeaseContract leaseContract);
}
