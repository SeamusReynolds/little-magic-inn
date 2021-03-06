package app.room;

public class Room {
    private String roomName;
    private Integer guestCapacity;
    private Integer storageCapacity;
    
    public Room(String roomName, Integer guestCapacity, Integer storageCapacity) {
        this.roomName = roomName;
        this.guestCapacity = guestCapacity;
        this.storageCapacity = storageCapacity;
    }
    
    public String getRoomName() {
        return roomName;
    }
    
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public Integer getGuestCapacity() {
        return guestCapacity;
    }
    
    public void setGuestCapacity(Integer guestCapacity) {
        this.guestCapacity = guestCapacity;
    }
    
    public Integer getStorageCapacity() {
        return storageCapacity;
    }
    
    public void setStorageCapacity(Integer storageCapacity) {
        this.storageCapacity = storageCapacity;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Room room = (Room) o;
    
        return getRoomName().equals(room.getRoomName());
    }
    
    @Override
    public int hashCode() {
        return getRoomName().hashCode();
    }
}
