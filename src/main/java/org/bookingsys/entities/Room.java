package org.bookingsys.entities;

public class Room {
    private int id;
    private String name;
    private String description;
    private double pricePerNight;
    private String status;

    public Room() {}

    public Room(int id, String name, String description, double pricePerNight, String status) {
        this.id = id;
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
