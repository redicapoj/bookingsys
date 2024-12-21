package org.bookingsys.entities;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private LocalDate reservationDate;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private double totalPayment;
    private Customer customer;
    private Room room;

    public Reservation() {}

    public Reservation(int id, LocalDate reservationDate, LocalDate checkinDate, LocalDate checkoutDate, double totalPayment, Customer customer, Room room) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.totalPayment = totalPayment;
        this.customer = customer;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDate=" + reservationDate +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", totalPayment=" + totalPayment +
                ", customer=" + customer +
                ", room=" + room +
                '}';
    }
}
