package app.util;

import app.booking.Booking;
import app.booking.BookingDao;
import app.cleaners.CleaningSquadDao;
import app.requests.AvailabilityInquiry;
import app.room.Room;
import app.room.RoomDao;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class ReservationLedger {
    
    private BookingDao bookingDao;
    private RoomDao roomDao;
    private CleaningSquadDao cleaningSquadDao;
    
    //Singleton pattern for potential multi-threaded expansion
    private static ReservationLedger instance = null;
    public static ReservationLedger instance() {
        if(instance == null) {
            synchronized(ReservationLedger.class) {
                if(instance == null) {
                    instance = new ReservationLedger();
                }
            }
        }
        return instance;
    }
    
    private ReservationLedger() {
        bookingDao = new BookingDao();
        roomDao = new RoomDao();
        cleaningSquadDao = new CleaningSquadDao();
    }
    
    public Set<Booking> getAvailableRooms(AvailabilityInquiry inquiry) {
        Set<Booking> bookingsForDate = getOrInitializeBookingsForDate(inquiry.getDate());
        Set<Booking> availableRooms = new TreeSet<>();
        
        for(Booking booking : bookingsForDate) {
            if(bookingValidForInquiry(booking, inquiry)) {
                availableRooms.add(booking);
            }
        }
        
        return availableRooms;
    }
    
    private boolean bookingValidForInquiry(Booking booking, AvailabilityInquiry inquiry) {
        //Set of validation rules
        if(booking.isFull()) { return false; }
        if(!inquiry.getDate().equals(booking.getDate())) { return false; }
        if(inquiry.getNumberOfGuests() > booking.availableBeds()) { return false; }
        if(inquiry.getAmountOfLuggage() > booking.availableStorage()) { return false; }
        
        return true;
    }
    
    private synchronized Set<Booking> getOrInitializeBookingsForDate(Date date) {
        Set<Booking> ret = bookingDao.getBookingsByDate(date);
        
        if(ret.isEmpty()) {
            for(Room room : roomDao.getAllRooms()) {
                addBooking(new Booking(date, room,0, 0));
            }
            ret = bookingDao.getBookingsByDate(date);
        }
        
        return ret;
    }
    
    private void addBooking(Booking booking) {
        Set<Booking> existingBookings = bookingDao.getBookingsByDate(booking.getDate());
        if(existingBookings.contains(booking)) {
            throw new RuntimeException("Tried to add booking for room/date that already has booking!");
        }
        bookingDao.addBookingToDate(booking);
    }
    
}
