package org.bookingsys.servlets;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bookingsys.entities.Room;
import org.bookingsys.managers.RoomManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {

    public RoomServlet() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("Inside RoomServlet...");

        // Enable CORS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        List<Room> rooms = null;
        try {
            // Fetch room data
            rooms = RoomManager.getInstance().loadRooms();

            if (rooms != null) {
                // Convert rooms to JSON
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(rooms);

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter out = response.getWriter();
                out.print(jsonResponse);
                out.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to fetch room data.");
            }

        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error retrieving room data.");
        }

        System.out.println("Data retrieved from the database.");
        if (rooms != null) {
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
    }
}

