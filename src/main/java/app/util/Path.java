package app.util;

public class Path {

    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Endpoint {
        public static final String AVAILABLE_ROOMS = "/api/v1/availableRooms";
        public static final String CREATE_BOOKING = "/api/v1/booking";
        public static final String CLEANING_SCHEDULE = "/api/v1/cleaningSchedule";
    }
}
