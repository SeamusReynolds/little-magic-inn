package app;

import app.booking.BookingDao;
import app.room.RoomDao;
import app.util.Filters;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Application {

    // Declare dependencies
    public static RoomDao roomDao;
    public static BookingDao bookingDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        roomDao = new RoomDao();
        bookingDao = new BookingDao();

        // Configure Spark
        port(8008);
        enableDebugScreen();

        // Set up routes
        get("*", (request, response) -> { response.status(404); return response; });

        //Set up after-filters (called after each get/post)
        after("*", Filters.addJsonHeader);

    }

}
