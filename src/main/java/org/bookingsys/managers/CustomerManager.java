package org.bookingsys.managers;

import org.bookingsys.dao.CustomerDao;
import org.bookingsys.entities.Customer;

public class CustomerManager {
    private static CustomerManager instance = new CustomerManager();

    private static CustomerDao dao = new CustomerDao();

    private CustomerManager() {}

    public static CustomerManager getInstance() {
        return instance;
    }

    public void addCustomer(String firstName, String lastName, String email, String phone, String licencePlate) {

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
            dao.saveCustomer(customer);
            System.out.println("Customer created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
        }
    }

    public void addCustomer(Customer customer) {

        try {
            dao.saveCustomer(customer);
            System.out.println("Customer created successfully!");
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
        }
    }

}
