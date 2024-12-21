package org.bookingsys.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bookingsys.managers.CustomerManager;

import java.io.IOException;

@WebServlet("/add-customer")
public class CustomerServlet extends HttpServlet {

    public CustomerServlet() {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Inside CustomerServlet doPost");

        // Enable CORS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Retrieve data from the frontend form (sent via POST)
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String licencePlate = request.getParameter("licencePlate");

        System.out.println("Data retrieved: " + firstName + ", " + lastName + ", " + email);

        // Validate input
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {

            // Send error response for invalid input
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid input! First name, last name, and email are required.");
            return;
        }

        try {
            CustomerManager.getInstance().addCustomer(firstName, lastName, email, phone, licencePlate);
            System.out.println("Customer created successfully!");

            // Success response
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Customer added successfully!");
        } catch (Exception e) {
            System.err.println("Error creating customer: " + e.getMessage());
            e.printStackTrace();

            // Error response
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to add customer. Please try again later.");
        }
    }
}


