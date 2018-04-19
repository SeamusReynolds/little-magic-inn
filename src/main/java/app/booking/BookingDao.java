package app.booking;

import java.util.*;

public class BookingDao {

    private static Map<Date, List<Booking>> bookings = new TreeMap<>();

    public Iterable<Booking> getAllBookings() {
        List<Booking> ret = new LinkedList<>();
        for(List<Booking> bookingsOnDate : bookings.values()) {
            ret.addAll(bookingsOnDate);
        }
        return ret;
    }

    public List<Booking> getBookingsByDate(Date date) {
        return bookings.get(date);
    }
}
