package app.booking;

import java.util.*;

public class BookingDao {

    private static Map<Date, Set<Booking>> bookings = new HashMap<>();

    public Set<Booking> getBookingsByDate(Date date) {
        return bookings.getOrDefault(date, new HashSet<>());
    }
    
    public void addBooking(Booking booking) {
        Set<Booking> bookingsOnDate = bookings.getOrDefault(booking.getDate(), new HashSet<>());
        bookingsOnDate.add(booking);
        bookings.put(booking.getDate(), bookingsOnDate);
    }
    
    public void updateBooking(Booking booking) {
        Set<Booking> bookings = getBookingsByDate(booking.getDate());
        if(bookings.isEmpty() || !bookings.contains(booking)) { addBooking(booking); }
        else {
            bookings.remove(booking);
            bookings.add(booking);
        }
    }
}
