package ss.week2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ss.week2.hotel.Password;
import ss.week2.hotel.Safe;

public class SafeTest {
    private Safe safeA;
    private Safe safeB;
    private Safe safeC;
    private Password passwordA;
    private Password passwordB;
    private Password passwordC;

    @Before
    public void setUp() throws Exception {
        passwordA = new Password();
        passwordB = new Password();
        passwordC = new Password();
        safeA = new Safe(passwordA);
        safeB = new Safe(passwordB);
        safeC = new Safe(passwordC);

        safeB.activate(Password.INITIAL);
        safeC.activate(Password.INITIAL);
        safeC.open(Password.INITIAL);
    }

    @Test
    public void testActivate() {
        safeA.activate("ilikemonkeys");
        safeB.activate("dragonsarescary");
        safeC.activate(Password.INITIAL);

        assertFalse("safeA active", safeA.isActive());
        assertTrue("safeB not active", safeB.isActive());
        assertTrue("safeC not active", safeC.isActive());

        safeA.activate(Password.INITIAL);

        assertTrue("safeA not active", safeA.isActive());
    }

    @Test
    public void testDeactivate() {
        safeA.deactivate();
        safeB.deactivate();
        safeC.deactivate();

        assertFalse("safeA active", safeA.isActive());
        assertFalse("safeB active", safeB.isActive());
        assertFalse("safeC active", safeC.isActive());
    }

    @Test
    public void testOpen() {
        safeA.open(Password.INITIAL);
        safeB.open("magic");
        safeC.open(Password.INITIAL);

        assertFalse("safeA open", safeA.isOpen());
        assertFalse("safeB open", safeB.isOpen());
        assertTrue("safeC not open", safeC.isOpen());

        safeA.open("kappa");
        safeB.open(Password.INITIAL);

        assertFalse("safeA open", safeA.isOpen());
        assertTrue("safeB not open", safeB.isOpen());

        safeA.activate(Password.INITIAL);
        safeA.open(Password.INITIAL);

        assertTrue("safeA not open", safeA.isOpen());
    }

    @Test
    public void testClose() {
        safeA.close();
        safeB.close();
        safeC.close();

        assertFalse("safeA open", safeA.isOpen());
        assertFalse("safeB open", safeB.isOpen());
        assertFalse("safeC open", safeC.isOpen());

        assertFalse("safeA active", safeA.isActive());
        assertTrue("safeB not active", safeB.isActive());
        assertTrue("safeC not active", safeC.isActive());
    }

    @Test
    public void testIsActive() {
        assertFalse("safeA active", safeA.isActive());
        assertTrue("safeB not active", safeB.isActive());
        assertTrue("safeC not active", safeC.isActive());
    }

    @Test
    public void testIsOpen() {
        assertFalse("safeA open", safeA.isOpen());
        assertFalse("safeB open", safeB.isOpen());
        assertTrue("safeC not open", safeC.isOpen());
    }

    @Test
    public void testGetPassword() {
        assertSame("safeA password", passwordA, safeA.getPassword());
        assertSame("safeB password", passwordB, safeB.getPassword());
        assertSame("safeC password", passwordC, safeC.getPassword());
    }

}
