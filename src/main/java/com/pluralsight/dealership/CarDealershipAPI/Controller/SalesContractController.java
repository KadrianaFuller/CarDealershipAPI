package com.pluralsight.dealership.CarDealershipAPI.Controller;

import com.pluralsight.dealership.CarDealershipAPI.Dao.SalesContractDao;
import com.pluralsight.dealership.CarDealershipAPI.Model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public class SalesContractController {

    private SalesContractDao salesContractDao;

    @Autowired
    public SalesContractController(SalesContractDao salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    @RequestMapping(path="/salesContracts", method = RequestMethod.GET)
    public List<SalesContract> getAllSalesContracts() {
        return this.salesContractDao.getAll();
    }

    @RequestMapping(path="/salesContracts/{id}", method = RequestMethod.GET)
    public SalesContract getASalesContract(@PathVariable int id) {
        return this.salesContractDao.getById(id);
    }

    @RequestMapping(path = "/salesContracts", method = RequestMethod.POST)
    public SalesContract addSalesContract(@RequestBody SalesContract salesContract) {
        return this.salesContractDao.insert(salesContract);
    }
}
