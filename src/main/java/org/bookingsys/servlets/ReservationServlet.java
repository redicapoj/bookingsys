package org.bookingsys.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bookingsys.entities.Customer;
import org.bookingsys.managers.CustomerManager;
import org.bookingsys.managers.ReservationManager;
import org.bookingsys.managers.RoomManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@WebServlet("/save-reservation")
public class ReservationServlet extends HttpServlet {

    public ReservationServlet() {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Enable CORS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Retrieve customer data from the frontend form (sent via POST)
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String licencePlate = request.getParameter("licencePlate");

        // Retrieve room data from the frontend
        String roomParam = request.getParameter("room");
        if (roomParam == null || roomParam.isEmpty()) {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Room ID is required!");
            return;
        }

        int roomId;
        try {
            roomId = Integer.parseInt(roomParam);
        } catch (NumberFormatException e) {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid Room ID!");
            return;
        }

        double pricePerNight = RoomManager.getInstance().getRoomPrice(roomId);

        // Retrieve reservation dates from the frontend (sent via POST)
        LocalDate checkinDate = null;
        LocalDate checkoutDate = null;
        try {
            checkinDate = LocalDate.parse(request.getParameter("checkinDate"));
            checkoutDate = LocalDate.parse(request.getParameter("checkoutDate"));
        } catch (DateTimeParseException e) {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid date format! Please use 'yyyy-MM-dd'.");
            return;
        }
        LocalDate currentDate = LocalDate.now();

        // Validate user input
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {

            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid input! First name, last name, and email are required.");
            return;
        }

        try {
            Customer customer = new Customer(firstName, lastName, email, phone, licencePlate);
            // Adding customer and retrieving its ID
            int customerId = CustomerManager.getInstance().addCustomer(customer);

            // Creating reservation
            int totalDays = (int) ChronoUnit.DAYS.between(checkinDate, checkoutDate);
            double totalPayment = totalDays * pricePerNight;

            // Save reservation
            ReservationManager.getInstance().saveReservation(currentDate, checkinDate, checkoutDate, totalPayment, customerId, roomId);

            // Invoking sending email functionality
            CustomerManager.getInstance().sendConfirmationEmail(email, firstName, roomParam, String.valueOf(checkinDate), String.valueOf(checkoutDate), totalPayment);

            // Success response
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Booking saved successfully!");
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to make booking. Please try again later.");
            e.printStackTrace();
        }
    }
}
