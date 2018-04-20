package app.cleaners;

public class CleaningSquad {
    public static final Double maximumHoursPerDay = 8.0;
    
    private String cleaningSquadName;
    private Double availableHours;
    private Integer payPerHour;
//    private Double hoursPerRoom;
//    private Double hoursPerPerson;
    
    public CleaningSquad(String cleaningSquadName, Double availableHours, Integer payPerHour) {
        this.cleaningSquadName = cleaningSquadName;
        if(availableHours > maximumHoursPerDay) {
            throw new RuntimeException("The Lord says the Gnomes may only work " + maximumHoursPerDay + " per day. " +
                                       "Lobby harder.");
        }
        this.availableHours = availableHours;
        this.payPerHour = payPerHour;
    }
    
    public String getCleaningSquadName() {
        return cleaningSquadName;
    }
    
    public void setCleaningSquadName(String cleaningSquadName) {
        this.cleaningSquadName = cleaningSquadName;
    }
    
    public Double getAvailableHours() {
        return availableHours;
    }
    
    public void setAvailableHours(Double availableHours) {
        this.availableHours = availableHours;
    }
    
    public Integer getPayPerHour() {
        return payPerHour;
    }
    
    public void setPayPerHour(Integer payPerHour) {
        this.payPerHour = payPerHour;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        
        CleaningSquad that = (CleaningSquad) o;
    
        return getCleaningSquadName().equals(that.getCleaningSquadName());
    }
    
    @Override
    public int hashCode() {
        return getCleaningSquadName().hashCode();
    }
}
