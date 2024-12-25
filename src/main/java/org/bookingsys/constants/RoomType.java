package org.bookingsys.constants;

public enum RoomType {
    SINGLE("single"),
    DOUBLE("double"),
    DELUXE("deluxe"),
    SUITE("suite"),;

    RoomType(String type) {
        this.type = type;
    }

    private String type;
    public String getType() {
        return type;
    }
}
