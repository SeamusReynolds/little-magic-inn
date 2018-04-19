package app.booking;

import app.room.Room;

import java.util.Date;

public class Booking {
    private Date date;
    private Room room;
    private Integer numberOfGuests;
    private Integer amountOfLuggage;
    
    public Booking(Date date, Room room, Integer numberOfGuests, Integer amountOfLuggage) {
        this.date = date;
        this.room = room;
        this.numberOfGuests = numberOfGuests;
        this.amountOfLuggage = amountOfLuggage;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }
    
    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
    
    public Integer getAmountOfLuggage() {
        return amountOfLuggage;
    }
    
    public void setAmountOfLuggage(Integer amountOfLuggage) {
        this.amountOfLuggage = amountOfLuggage;
    }
}
