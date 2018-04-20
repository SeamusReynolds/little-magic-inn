package app.util;

import app.booking.Booking;
import app.booking.BookingDao;
import app.cleaners.CleaningSquadDao;
import app.requests.AvailabilityInquiry;
import app.requests.BookingRequest;
import app.room.Room;
import app.room.RoomDao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

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
    
    public Booking makeReservation(BookingRequest bookingRequest) {
        Booking bestRoom = getBestRoom(bookingRequest);
        if(bestRoom != null) {
            bestRoom.addGuests(bookingRequest.getNumberOfGuests());
            bestRoom.addLuggage(bookingRequest.getAmountOfLuggage());
            bookingDao.updateBooking(bestRoom);
        }
        
        return bestRoom;
    }
    
    private Booking getBestRoom(BookingRequest bookingRequest) {
        Set<Booking> availableRooms = getAvailableRooms(bookingRequest);
        
        //If there's a tie in points, it's fine to just pick one room for it, so we overwrite rooms that already
        //had that score previously.
        TreeMap<Integer, Booking> roomRankings = new TreeMap<>();
        
        //Assign each room a number of points based on how close to full this booking request would make it.
        //The more points, the farther away from full it is. Try to get as close to a full room as possible.
        for(Booking availableRoom : availableRooms) {
            int points = 0;
            points += availableRoom.availableBeds() - bookingRequest.getNumberOfGuests();
            points += availableRoom.availableStorage() - bookingRequest.getAmountOfLuggage();
            roomRankings.put(points, availableRoom);
        }
        
        return roomRankings.isEmpty() ? null : roomRankings.firstEntry().getValue();
    }
    
    public Set<Booking> getAvailableRooms(AvailabilityInquiry inquiry) {
        Set<Booking> bookingsForDate = getOrInitializeBookingsForDate(inquiry.getDate());
        Set<Booking> availableRooms = new HashSet<>();
        
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
        bookingDao.addBooking(booking);
    }
    
}
