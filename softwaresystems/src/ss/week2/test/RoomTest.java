package ss.week2.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ss.week2.hotel.Guest;
import ss.week2.hotel.Password;
import ss.week2.hotel.Room;
import ss.week2.hotel.Safe;

/**
 * Testprogram for Room en Guest Software Systems.
 * @author Arend Rensink
 * @version $Revision: 1.5 $
 */
public class RoomTest {
    /** <tt>Gast</tt>-testvariabele. */
    public Guest guest;
    /** <tt>Kamer</tt>-testvariabele. */
    public Room room;
    public Safe safe;

    @Before
    public void setUp() {
        Password password = new Password();

        safe = new Safe(password);
        // initialisatie van gast-variabele
        guest = new Guest("Jip");
        // initialisatie van kamer-variabele
        room = new Room(101, safe);
    }

    /**
     * Test of the initial situation Method call should be prefixed by setUp <tt>{@link #setUp}</tt>
     * .
     */
    @Test
    public void testInitial() {
        assertEquals("room.getNumber()", 101, room.getNumber());
        assertEquals("room.getSafe()", safe, room.getSafe());
    }

    /**
     * Test setting a guest Method call should be prefixed by setUp <tt>{@link #setUp}</tt>.
     */
    @Test
    public void testSetGuest() {
        room.setGuest(guest);
        assertEquals("room.setueast(gast); room.getGuest()", guest, room.getGuest());
    }

}
