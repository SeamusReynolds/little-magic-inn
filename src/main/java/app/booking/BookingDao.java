package app.booking;

import java.util.*;

public class BookingDao {

    private static Map<Date, Set<Booking>> bookings = new HashMap<>();

    public Set<Booking> getBookingsByDate(Date date) {
        return bookings.getOrDefault(date, new HashSet<>());
    }
    
    public void addBookingToDate(Booking booking) {
        bookings.getOrDefault(booking.getDate(), new HashSet<>()).add(booking);
    }
}
