package org.bookingsys.dao;

import org.bookingsys.connection.DatabaseConnection;
import org.bookingsys.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDao {

    private DatabaseConnection dbConnection;

    public CustomerDao() {
        dbConnection = DatabaseConnection.getInstance();
    }

    public void saveCustomer(Customer customer) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String query = "INSERT INTO clients (first_name, last_name, email, phone_number, licence_plate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getLicencePlate());
            stmt.executeUpdate();
        }
    }
}


