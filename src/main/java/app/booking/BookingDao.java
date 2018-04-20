package app.booking;

import java.util.*;

public class BookingDao {

    private static Map<Date, Set<Booking>> bookings = new HashMap<>();

    public Set<Booking> getBookingsByDate(Date date) {
        return bookings.getOrDefault(date, new HashSet<>());
    }
    
    public void addBookingToDate(Booking booking) {
        Set<Booking> bookingsOnDate = bookings.getOrDefault(booking.getDate(), new HashSet<>());
        bookingsOnDate.add(booking);
        bookings.put(booking.getDate(), bookingsOnDate);
    }
}
