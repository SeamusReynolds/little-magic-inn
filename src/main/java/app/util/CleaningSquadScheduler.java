package app.util;

import app.booking.Booking;
import app.cleaners.CleaningSquad;
import app.cleaners.CleaningSquadDao;
import app.cleaners.CleaningSquadShift;

import java.util.*;

public class CleaningSquadScheduler {
    
    private final static Double hoursPerRoom = 1.0;
    private final static Double hoursPerPerson = 0.5;
    
    private CleaningSquadDao cleaningSquadDao;
    
    //Singleton pattern for potential multi-threaded expansion
    private static CleaningSquadScheduler instance = null;
    public static CleaningSquadScheduler instance() {
        if(instance == null) {
            synchronized(CleaningSquadScheduler.class) {
                if(instance == null) {
                    instance = new CleaningSquadScheduler();
                }
            }
        }
        return instance;
    }
    
    private CleaningSquadScheduler() {
        cleaningSquadDao = new CleaningSquadDao();
    }
    
    public Collection<CleaningSquadShift> getCleaningSquadShifts(Date date) {
        Set<Booking> roomsToClean = ReservationLedger.instance().getActiveBookingsForDate(date);
        Set<CleaningSquad> cleaningSquads = cleaningSquadDao.getAllCleaningSquads();
        Map<CleaningSquad, CleaningSquadShift> cleaningSquadShifts = new HashMap<>();
        
        for(CleaningSquad cleaningSquad : cleaningSquads) {
            Set<Booking> roomsAssigned = new HashSet<>();
            Double timeRequired = 0.0;
            for(Booking booking : roomsToClean) {
                Double result = timeRequired + getCleaningTimeForBooking(booking);
                
                //If this is going to put us over, skip it, but keep iterating to see if we can fit other rooms.
                if(result > cleaningSquad.getAvailableHours()) {
                    continue;
                } else if(result <= cleaningSquad.getAvailableHours()) { //Add the room since it fits
                    timeRequired = result;
                    roomsAssigned.add(booking);
                }
                //If we've used up all available hours, stop iterating.
                if(timeRequired.equals(cleaningSquad.getAvailableHours())) {
                    break;
                }
            }
            cleaningSquadShifts.put(cleaningSquad,
                                    new CleaningSquadShift(cleaningSquad, date, roomsAssigned, timeRequired));
            roomsToClean.removeAll(roomsAssigned);
        }
        
        return cleaningSquadShifts.values();
        
    }
    
    private Double getCleaningTimeForBooking(Booking booking) {
        return hoursPerRoom + (booking.getNumberOfGuests() * hoursPerPerson);
    }
    
}
