package app.cleaners;

import app.booking.Booking;

import java.util.Date;
import java.util.Set;

public class CleaningSquadShift {
    private CleaningSquad cleaningSquad;
    private Date date;
    private Set<Booking> assignments;
    private Double timeRequired;
    
    public CleaningSquadShift(CleaningSquad cleaningSquad, Date date, Set<Booking> assignments, Double timeRequired) {
        this.cleaningSquad = cleaningSquad;
        this.date = date;
        this.assignments = assignments;
        this.timeRequired = timeRequired;
    }
    
    public CleaningSquad getCleaningSquad() {
        return cleaningSquad;
    }
    
    public void setCleaningSquad(CleaningSquad cleaningSquad) {
        this.cleaningSquad = cleaningSquad;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Set<Booking> getAssignments() {
        return assignments;
    }
    
    public void setAssignments(Set<Booking> assignments) {
        this.assignments = assignments;
    }
    
    public Double getTimeRequired() {
        return timeRequired;
    }
    
    public void setTimeRequired(Double timeRequired) {
        this.timeRequired = timeRequired;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        
        CleaningSquadShift that = (CleaningSquadShift) o;
        
        if(getCleaningSquad() != null ? !getCleaningSquad().equals(that.getCleaningSquad()) :
           that.getCleaningSquad() != null) {
            return false;
        }
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }
    
    @Override
    public int hashCode() {
        int result = getCleaningSquad() != null ? getCleaningSquad().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
