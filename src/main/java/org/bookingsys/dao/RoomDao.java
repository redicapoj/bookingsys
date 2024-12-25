package org.bookingsys.dao;

import org.bookingsys.connection.DatabaseConnection;
import org.bookingsys.constants.RoomStatus;
import org.bookingsys.constants.RoomType;
import org.bookingsys.entities.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    DatabaseConnection databaseConnection;

    public RoomDao() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public List<Room> fetchRooms() throws SQLException {
        String query = "SELECT * FROM rooms";
        List<Room> rooms = new ArrayList<>();

        try(Connection connection = databaseConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("room_id");
                String name = rs.getString("name");
                RoomType type = RoomType.valueOf(rs.getString("room_type"));
                String description = rs.getString("description");
                double pricePerNight = rs.getDouble("night_price");
                RoomStatus status = RoomStatus.valueOf(rs.getString("status"));

                Room room = new Room(id, name, type, description, pricePerNight, status);
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return rooms;
    }

    public double getRoomPrice(int roomId) {
        String query = "SELECT night_price FROM rooms WHERE room_id = ?";
        double pricePerNight = 0.0;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pricePerNight = rs.getDouble("night_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pricePerNight;
    }

}
