package ss.week3.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    private final String name;
    private final Password password;
    private final List<Room> freeRooms;
    private final Map<String, Room> usedRooms;

    public Hotel(String argName) {
        assert argName != null;

        this.name = argName;
        this.password = new Password();
        this.freeRooms = new ArrayList<Room>();
        this.usedRooms = new HashMap<String, Room>();

        freeRooms.add(new Room(100, new Safe(password)));
        freeRooms.add(new Room(101, new Safe(password)));
    }

    public Room checkIn(String argPassword, String guestName) {
        assert argPassword != null;
        assert guestName != null;

        if (!password.testWord(argPassword) || usedRooms.containsKey(guestName)) {
            return null;
        }

        Room freeRoom = getFreeRoom();

        if (freeRoom == null) {
            return null;
        }

        Guest newGuest = new Guest(guestName);
        newGuest.checkin(freeRoom);

        usedRooms.put(guestName, freeRoom);
        freeRooms.remove(freeRoom);

        return freeRoom;
    }

    public void checkOut(String guestName) {
        assert guestName != null;

        if (usedRooms.containsKey(guestName)) {
            Room room = usedRooms.get(guestName);

            room.getSafe().deactivate();
            room.getGuest().checkout();

            freeRooms.add(room);
            usedRooms.remove(guestName);
        }
    }

    public Room getFreeRoom() {
        return freeRooms.size() > 0 ? freeRooms.get(0) : null;
    }

    public Room getRoom(String guestName) {
        assert guestName != null;

        return usedRooms.containsKey(guestName) ? usedRooms.get(guestName) : null;
    }

    public Password getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Status of hotel " + name + "\n");
        builder.append("Free rooms\n");

        for (Room room : freeRooms) {
            builder.append(room.getNumber());
            builder.append('\n');
        }

        builder.append("Used rooms\n");

        for (Room room : usedRooms.values()) {
            builder.append(room.getNumber());
            builder.append(" by ");
            builder.append(room.getGuest().getName());
            builder.append(" safe activated: ");
            builder.append(room.getSafe().isActive());
            builder.append(" safe open: ");
            builder.append(room.getSafe().isOpen());
            builder.append('\n');
        }

        return builder.toString();
    }
}
