package dungeon;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Room> rooms;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.rooms = new ArrayList<>();
    }

    // ENCAPSULAMIENTO
    public int getFloorNumber() { return floorNumber; }
    public List<Room> getRooms() { return rooms; }
    public Room getRoom(int index) { return rooms.get(index); }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public int getTotalRooms() {
        return rooms.size();
    }
}