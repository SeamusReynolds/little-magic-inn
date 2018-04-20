package app;

import app.booking.BookingController;
import app.cleaners.CleaningSquadController;
import app.exception.ErrorResponseException;
import app.room.RoomController;
import app.util.Filters;
import app.util.JsonUtil;
import app.util.Path;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Application {

    public static void main(String[] args) {
        // Configure Spark
        port(8008);
        
        // Set up routes
        post(Path.Endpoint.AVAILABLE_ROOMS, new RoomController().getAvailableRooms);
        post(Path.Endpoint.CREATE_BOOKING, new BookingController().makeReservation);
        get(Path.Endpoint.CLEANING_SCHEDULE, new CleaningSquadController().getCleaningSchedule);
        
        //Handle exceptions
        exception(ErrorResponseException.class, (exception, request, response) -> {
            ErrorResponseException errorResponse = (ErrorResponseException) exception;
            response.status(errorResponse.getStatusCode());
            
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("errorMsg", errorResponse.getMessage());
            response.body(JsonUtil.dataToJson(errorBody));
        });
        
        exception(RuntimeException.class, (exception, request, response) -> {
            exception.printStackTrace();
            response.status(500);
            response.body("INTERNAL SERVER ERROR\n\n" + ExceptionUtils.getStackTrace(exception));
        });
        
        enableDebugScreen();

        //Set up after-filters (called after each get/post)
        after("*", Filters.addJsonHeaders);

    }

}
