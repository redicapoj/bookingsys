package org.bookingsys.managers;

import org.bookingsys.dao.RoomDao;
import org.bookingsys.entities.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private static final RoomManager instance = new RoomManager();

    private RoomManager() {}

    public static RoomManager getInstance() {
        return instance;
    }

    private final RoomDao dao = new RoomDao();

    public List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            rooms = dao.fetchRooms();
            return rooms;
        } catch (Exception e) {
            System.err.println("Error loading rooms: " + e.getMessage());
            e.printStackTrace();
        }
        return rooms;
    }

    public double getRoomPrice(int roomId) {
        try {
            double pricePerNight = dao.getRoomPrice(roomId);
            return pricePerNight;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
