package app;

import app.booking.BookingDao;
import app.exception.ErrorResponseException;
import app.room.RoomController;
import app.room.RoomDao;
import app.util.Filters;
import app.util.JsonUtil;
import app.util.Path;

import java.util.HashMap;
import java.util.Map;

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
        

        // Set up routes
        get(Path.Endpoint.AVAILABLE_ROOMS, RoomController.getAvailableRooms);
        
        exception(ErrorResponseException.class, (exception, request, response) -> {
            ErrorResponseException errorResponse = (ErrorResponseException) exception;
            response.status(errorResponse.getStatusCode());
            
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("errorMsg", errorResponse.getMessage());
            response.body(JsonUtil.dataToJson(errorBody));
        });
        
        exception(RuntimeException.class, (exception, request, response) -> {
            response.status(500);
            response.body("INTERNAL SERVER ERROR\n\n" + exception);
        });
        
        enableDebugScreen();

        //Set up after-filters (called after each get/post)
        after("*", Filters.addJsonHeader);

    }

}
