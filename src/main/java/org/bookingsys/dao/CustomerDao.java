package org.bookingsys.dao;

import org.bookingsys.connection.DatabaseConnection;
import org.bookingsys.entities.Customer;

import java.sql.*;

public class CustomerDao {

    private DatabaseConnection dbConnection;

    public CustomerDao() {
        dbConnection = DatabaseConnection.getInstance();
    }

    public int saveCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO clients (first_name, last_name, email, phone_number, licence_plate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getLicencePlate());

            stmt.executeUpdate();

            // Retrieve the generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve customer ID.");
                }
            }
        }
    }

}


