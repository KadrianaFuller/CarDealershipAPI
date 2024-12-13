package com.pluralsight.dealership.CarDealershipAPI.Dao;

import com.pluralsight.dealership.CarDealershipAPI.Model.SalesContract;

import javax.sql.DataSource;
import java.sql.*;
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
        SalesContract salesContract = null;
        String sql = "SELECT contract_id, vin, customer_name, sale_date, sale_price FROM sales_contract WHERE contract_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id); // Set the contract_id as the parameter
            ResultSet rows = statement.executeQuery();
            while(rows.next()){
                salesContract = new SalesContract(rows.getString(1), rows.getString(2), rows.getDate(3),rows.getDouble(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesContract;
    }


    @Override
    public SalesContract add(SalesContract salesContract) {
        SalesContract createdSalesContract = null;
        String sql = "INSERT INTO sales_contract (vin, customer_name, sale_date, sale_price) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set parameters for the SalesContract object
            statement.setString(1, salesContract.getVehicleSold().getVin());
            statement.setString(2, salesContract.getCustomerName()); // Customer name
            statement.setDate(3, Date.valueOf(salesContract.getDate())); // Sale date
            statement.setDouble(4, salesContract.getTotalPrice()); // Sale price

            // Execute the update and get generated keys
            statement.executeUpdate();

            // Retrieve generated contract ID and set it in the SalesContract object
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                createdSalesContract = getById(generatedKeys.getInt(1));  // Assuming getById method exists to fetch by ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createdSalesContract;
    }



    @Override
    public Boolean update(SalesContract salesContract) {
        int vinPos = 0;
        int customerNamePos = 0;
        int saleDatePos = 0;
        int salePricePos = 0;
        int idPos = 0;
        String updateParamStatement = "";
        // Check which properties are not null or empty and build the update statement accordingly
        if (salesContract.getVehicleSold() != null) {  // Assuming vehicleSold is a VIN
            vinPos += 1;
            idPos++;
            updateParamStatement += "vin=? ";
        }
        if (salesContract.getCustomerName() != null && !salesContract.getCustomerName().isEmpty()) {
            customerNamePos += vinPos + 1;
            idPos++;
            String comma = "";
            if (updateParamStatement.length() > 0) {
                comma = ",";
            }
            updateParamStatement += comma + "customerName=? ";
        }
        if (salesContract.getDate() != null) {
            saleDatePos += customerNamePos + vinPos + 1;
            idPos++;
            String comma = "";
            if (updateParamStatement.length() > 0) {
                comma = ",";
            }
            updateParamStatement += comma + "saleDate=? ";
        }
        if (salesContract.getTotalPrice() != 0.0) {
            salePricePos += saleDatePos + customerNamePos + vinPos + 1;
            idPos++;
            String comma = "";
            if (updateParamStatement.length() > 0) {
                comma = ",";
            }
            updateParamStatement += comma + "salePrice=? ";
        }
        String sql = "UPDATE sales_contract SET " + updateParamStatement + " WHERE contract_id=?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            // Set the values based on which parameters are not null or 0
            if (salesContract.getVehicleSold() != null) {
                statement.setString(vinPos, salesContract.getVehicleSold().getVin());  // Assuming vehicleSold has a getVin() method
            }

            if (salesContract.getCustomerName() != null && !salesContract.getCustomerName().isEmpty()) {
                statement.setString(customerNamePos, salesContract.getCustomerName());
            }

            if (salesContract.getDate() != null) {
                statement.setDate(saleDatePos, Date.valueOf(salesContract.getDate()));  // Assuming saleDate is a LocalDate
            }

            if (salesContract.getTotalPrice() != 0.0) {
                statement.setDouble(salePricePos, salesContract.getTotalPrice());
            }

            int id = 0;
            statement.setInt(idPos + 1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public SalesContract delete(int id) {
        return null;
    }
}
