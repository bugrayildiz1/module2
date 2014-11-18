package ss.week3.hotel;

/**
 * Represents a guest which has a name and a room.
 * @author ciske
 *
 */
public class Guest {
    private final String name;
    private Room room;

    /**
     * Create a new <code>Guest</code> with the specified name and no <code>Room</code>.
     * @param argName The name of the <code>Guest</code>.
     */
    public Guest(String argName) {
        this.name = argName;
        this.room = null;
    }

    /**
     * Checks this <code>Guest</code> into the specified <code>Room</code>. The <code>Room</code> is
     * claimed by setting the <code>Guest</code> reference to this instance. After calling this
     * method the <code>Room</code> of this <code>Guest</code> will be set to the specified
     * <code>Room</code> if the check-in was successful. It is not possible to check into an already
     * claimed <code>Room</code> or check in when this <code>Guest</code> is already checked into a
     * <code>Room</code>.
     * @param argRoom The <code>Room</code> to check this <code>Guest</code> into.
     * @return <code>true</code> if this <code>Guest</code> was successfully checked into the
     * specified <code>Room</code>; <code>false</code> if this <code>Guest</code> could not be
     * checked into the specified <code>Room</code>
     */
    public boolean checkin(Room argRoom) {
        if (room != null) {
            // This guest already has a room.
            return false;
        }
        if (argRoom.getGuest() != null) {
            // The room already has a guest.
            return false;
        }

        // Claim the room.
        argRoom.setGuest(this);
        room = argRoom;

        return true;
    }

    /**
     * Checks this <code>Guest</code> out of the <code>Room</code> and frees the <code>Room</code>
     * by setting the <code>Guest</code> reference to <code>null</code>. After calling this method
     * the <code>Room</code> of this <code>Guest</code> will be <code>null</code>.
     * @return <code>true</code> if this <code>Guest</code> was successfully checked out;
     * <code>false</code> if this <code>Guest</code> has no <code>Room</code>
     */
    public boolean checkout() {
        if (room == null) {
            // This guest doesn't have a room.
            return false;
        }

        // Free the room.
        room.setGuest(null);
        room = null;

        return true;
    }

    /**
     * Returns the name of this <code>Guest</code>.
     * @return the name of this <code>Guest</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the <code>Room</code> of this <code>Guest</code>.
     * @return the <code>Room</code> of this <code>Guest</code>; <code>null</code> if this
     * <code>Guest</code> has no <code>Room</code>
     */
    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Guest " + name;
    }
}
