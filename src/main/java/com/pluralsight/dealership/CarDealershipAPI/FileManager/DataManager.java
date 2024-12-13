package com.pluralsight.dealership.CarDealershipAPI.FileManager;


import com.pluralsight.dealership.CarDealershipAPI.Model.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private BasicDataSource dataSource;


    public DataManager(String username, String password) {
        this.dataSource = new BasicDataSource();
        this.dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership");
        this.dataSource.setUsername(username);
        this.dataSource.setPassword(password);
    }

    public List<Vehicle> viewAllVehicles() {
        List<Vehicle> vehicle = new ArrayList<>();
        String query = "SELECT * FROM inventory";


        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                String vin = resultSet.getString("vin");
                String id = resultSet.getString(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle ;
    }


}
