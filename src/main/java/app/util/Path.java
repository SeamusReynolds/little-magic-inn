package app.util;

public class Path {

    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Endpoint {
        public static final String AVAILABLE_ROOMS = "/availableRooms";
        public static final String CREATE_BOOKING = "/booking";
        public static final String CLEANING_SCHEDULE = "/cleaningSchedule";
    }
}
