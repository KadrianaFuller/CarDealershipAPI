package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.Inventory;

import java.util.List;

public interface InventoryDao {

    List<Inventory> getAllInventory();
    Inventory getById(int id);
    Inventory  add(Inventory inventory);
    Inventory update(Inventory inventory);
    Inventory delete(int id);

}
