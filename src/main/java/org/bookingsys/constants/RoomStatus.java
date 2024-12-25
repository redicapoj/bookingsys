package org.bookingsys.constants;

public enum RoomStatus {
    AVAILABLE("available"),
    BOOKED("booked"),
    PENDING("pending");

    RoomStatus(String status) {
        this.status = status;
    }

    private String status;
    public String getStatus() {
        return status;
    }
}
