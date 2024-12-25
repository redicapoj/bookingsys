package org.bookingsys.dao;

import org.bookingsys.connection.DatabaseConnection;
import org.bookingsys.entities.Customer;
import org.bookingsys.entities.Reservation;
import org.bookingsys.entities.Room;

import java.sql.*;

public class ReservationDao {
    private DatabaseConnection databaseConnection;

    public ReservationDao() {
        databaseConnection =  DatabaseConnection.getInstance();
    }

    public void saveReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (reservation_date, checkin_date, checkout_date, total_payment, client_id, room_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(reservation.getReservationDate()));
            stmt.setDate(2, Date.valueOf(reservation.getCheckinDate()));
            stmt.setDate(3, Date.valueOf(reservation.getCheckoutDate()));
            stmt.setDouble(4, reservation.getTotalPayment());
            stmt.setInt(5, reservation.getCustomer());
            stmt.setInt(6, reservation.getRoom());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
