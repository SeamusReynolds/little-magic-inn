package app.requests;

import java.util.Date;

public class AvailabilityInquiry {
    private Date date;
    private Integer numberOfGuests;
    private Integer amountOfLuggage;
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
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
