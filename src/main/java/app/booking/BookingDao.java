package app.booking;

import java.util.*;

public class BookingDao {

    private static Map<Date, Set<Booking>> bookings = new TreeMap<>();

    public Set<Booking> getBookingsByDate(Date date) {
        return bookings.getOrDefault(date, new TreeSet<>());
    }
    
    public void addBookingToDate(Booking booking) {
        bookings.getOrDefault(booking.getDate(), new TreeSet<>()).add(booking);
    }
}
