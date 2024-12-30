package org.bookingsys.managers;

import org.bookingsys.dao.CustomerDao;
import org.bookingsys.entities.Customer;
import org.bookingsys.util.EmailService;

public class CustomerManager {
    private static CustomerManager instance = new CustomerManager();

    private static CustomerDao dao = new CustomerDao();

    private CustomerManager() {}

    public static CustomerManager getInstance() {
        return instance;
    }

    public int addCustomer(String firstName, String lastName, String email, String phone, String licencePlate) {

        if (firstName == null || lastName == null || email == null || phone == null || licencePlate == null) {
            throw new IllegalArgumentException("Customer details cannot be null");
        }

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setLicencePlate(licencePlate);

        try {
            int customerId = dao.saveCustomer(customer);
            System.out.println("Customer created successfully.");
            return customerId;
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
            throw new RuntimeException("Failed to create customer.");
        }
    }

    public int addCustomer(Customer customer) {
        try {
            int customerId = dao.saveCustomer(customer);
            System.out.println("Customer created successfully.");
            return customerId;
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
            throw new RuntimeException("Failed to create customer.");
        }
    }

    public void sendConfirmationEmail(String email, String customerName, String room, String checkinDate, String checkoutDate, double totalPayment) {
        EmailService.getInstance().sendConfirmationEmail(email, customerName, room, checkinDate, checkoutDate, totalPayment);
    }
}
