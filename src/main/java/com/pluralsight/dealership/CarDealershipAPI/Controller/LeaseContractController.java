package com.pluralsight.dealership.CarDealershipAPI.Controller;

import com.pluralsight.dealership.CarDealershipAPI.Dao.LeaseContractDao;
import com.pluralsight.dealership.CarDealershipAPI.Model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaseContractController {

    private LeaseContractDao leaseContractDao;

    @Autowired
    public LeaseContractController(LeaseContractDao leaseContractDao) {
        this.leaseContractDao = leaseContractDao;
    }

    @RequestMapping(path="/leaseContracts", method = RequestMethod.GET)
    public List<LeaseContract> getAllLeaseContracts() {
        return this.leaseContractDao.getAll();
    }

    @RequestMapping(path="/leaseContracts/{id}", method = RequestMethod.GET)
    public LeaseContract getALeaseContract(@PathVariable int id) {
        return this.leaseContractDao.getById(id);
    }

    @RequestMapping(path = "/leaseContracts", method = RequestMethod.POST)
    public LeaseContract addLeaseContract(@RequestBody LeaseContract leaseContract) {
        return this.leaseContractDao.insert(leaseContract);
    }
}