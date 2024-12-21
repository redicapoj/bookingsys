package org.bookingsys.action;

import org.bookingsys.entities.Customer;
import org.bookingsys.managers.CustomerManager;

public class View {
    public static Customer createCustomer(String firstName, String lastName, String email, String phone, String licencePlate) {
        System.out.println("\n1. Instance of customer is being created...");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setLicencePlate(licencePlate);

        return customer;
    }

    public static void saveCustomer(Customer customer) {
        System.out.println("\n2. Instance of customer is being saved...");

        CustomerManager.getInstance().addCustomer(customer);
    }
}
