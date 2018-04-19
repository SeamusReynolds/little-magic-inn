package app.room;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class RoomDao {

    private final List<Room> rooms = ImmutableList.of(
            new Room("Room 1", 2, 1),
            new Room("Room 2", 2, 0),
            new Room("Room 3", 1, 2),
            new Room("Room 4", 1, 0)
    );

    public Iterable<Room> getAllRooms() {
        return rooms;
    }
}
