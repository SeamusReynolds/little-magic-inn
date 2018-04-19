package app.cleaners;

public class CleaningSquad {
    private static final Integer maximumHoursPerDay = 8;
    
    private Integer availableHours;
    private Double hoursPerRoom;
    private Double hoursPerPerson;
    
    public CleaningSquad(Integer availableHours, Double hoursPerRoom, Double hoursPerPerson) {
        this.availableHours = availableHours > maximumHoursPerDay ? maximumHoursPerDay : availableHours;
        this.hoursPerRoom = hoursPerRoom;
        this.hoursPerPerson = hoursPerPerson;
    }
    
    public Integer getAvailableHours() {
        return availableHours;
    }
    
    public void setAvailableHours(Integer availableHours) {
        this.availableHours = availableHours;
    }
    
    public Double getHoursPerRoom() {
        return hoursPerRoom;
    }
    
    public void setHoursPerRoom(Double hoursPerRoom) {
        this.hoursPerRoom = hoursPerRoom;
    }
    
    public Double getHoursPerPerson() {
        return hoursPerPerson;
    }
    
    public void setHoursPerPerson(Double hoursPerPerson) {
        this.hoursPerPerson = hoursPerPerson;
    }
}
