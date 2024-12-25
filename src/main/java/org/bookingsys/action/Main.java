package org.bookingsys.action;

import org.bookingsys.connection.DatabaseConnection;
import org.bookingsys.dao.RoomDao;
import org.bookingsys.entities.Customer;
import org.bookingsys.entities.Room;
import org.bookingsys.managers.CustomerManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String[] input() {
        Scanner scanner = new Scanner(System.in);

        String[] data = new String[5];
        String firstName;
        String lastName;
        String email;
        String phone;
        String licencePlate;

        System.out.println("Enter customer data.\n");

        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        phone = scanner.nextLine();
        System.out.print("Enter licence plate: ");
        licencePlate = scanner.nextLine();

        data[0] = firstName;
        data[1] = lastName;
        data[2] = email;
        data[3] = phone;
        data[4] = licencePlate;

        return data;
    }

    private static Customer createCustomer(String[] data) {
        String firstName = data[0];
        String lastName = data[1];
        String email = data[2];
        String phone = data[3];
        String licencePlate = data[4];

        return View.createCustomer(firstName, lastName, email, phone, licencePlate);
    }

    public static void saveCustomer(Customer customer) {
        CustomerManager.getInstance().addCustomer(customer);
    }

    public static void testingRoomFetcher() throws SQLException {
        RoomDao roomDao = new RoomDao();
        List<Room> rooms = roomDao.fetchRooms();
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public static void main(String[] args) {

        // Connecting with DB
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        try(Connection con = databaseConnection.getConnection()) {
            System.out.println("Connected to database");

            // Starting the program
            /*String[] data = input();
            Customer customer = createCustomer(data);
            saveCustomer(customer);*/

            testingRoomFetcher();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
