package org.bookingsys.managers;

import org.bookingsys.dao.CustomerDao;
import org.bookingsys.dao.ReservationDao;
import org.bookingsys.entities.Reservation;

import java.time.LocalDate;

public class ReservationManager {
    private static final ReservationManager instance = new ReservationManager();

    private static final ReservationDao dao = new ReservationDao();

    private ReservationManager() {}

    public static ReservationManager getInstance() {
        return instance;
    }

    public void saveReservation(LocalDate currentDate, LocalDate checkinDate, LocalDate checkoutDate, double totalPayment, int customerId, int roomId) {
        if (currentDate == null || checkinDate == null || checkoutDate == null || totalPayment == 0.0 || customerId == 0 || roomId == 0) {
            throw new IllegalArgumentException("Reservation details cannot be null");
        }

        Reservation reservation = new Reservation();
        reservation.setReservationDate(currentDate);
        reservation.setCheckinDate(checkinDate);
        reservation.setCheckoutDate(checkoutDate);
        reservation.setTotalPayment(totalPayment);
        reservation.setCustomer(customerId);
        reservation.setRoom(roomId);

        try {
            dao.saveReservation(reservation);
            System.out.println("Reservation created successfully.");
        } catch (Exception e) {
            System.out.println("Error while saving reservation: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
