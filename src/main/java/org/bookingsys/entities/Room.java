package org.bookingsys.entities;

import org.bookingsys.constants.RoomStatus;
import org.bookingsys.constants.RoomType;

public class Room {
    private int id;
    private String name;
    private RoomType type;
    private String description;
    private double pricePerNight;
    private RoomStatus status;

    public Room() {}

    public Room(int id, String name, RoomType type, String description, double pricePerNight, RoomStatus status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", status='" + status + '\'' +
                '}';
    }
}
