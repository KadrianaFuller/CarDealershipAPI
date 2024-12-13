package com.pluralsight.dealership.CarDealershipAPI.Controller;

import com.pluralsight.dealership.CarDealershipAPI.Dao.VehiclesDao;
import com.pluralsight.dealership.CarDealershipAPI.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehiclesController {

    private VehiclesDao vehiclesDao;

    @Autowired
    public VehiclesController(VehiclesDao vehiclesDao) {
        this.vehiclesDao = vehiclesDao;
    }

    @RequestMapping(path="/vehicles", method = RequestMethod.GET)
    public List<Vehicle> getMake() {
        return this.vehiclesDao.getAll();
    }

    @RequestMapping(path="/vehicles/{id}", method = RequestMethod.GET)
    public Vehicle getModel(@PathVariable int id) {
        return this.vehiclesDao.getById(id);
    }

    @RequestMapping(path="/vehicles", method = RequestMethod.POST)
    public Vehicle addAVehicle(@RequestBody Vehicle vehicle) {
        return this.vehiclesDao.insert(vehicle);
    }

    @RequestMapping(path="/vehicles/{id}", method=RequestMethod.PUT)
    public Vehicle updateAVehicle (@PathVariable int id, @RequestBody Vehicle vehicle) {
        return this.vehiclesDao.update(vehicle);
    }
}