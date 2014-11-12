package ss.week1;

import ss.utils.TestRunner;

/**
 * Test class for the TreeWayLamp class.
 * @author ciske
 *
 */
public class ThreeWayLampTests extends TestRunner {
    public static void main(String[] args) {
        ThreeWayLampTests tests = new ThreeWayLampTests();

        tests.runTests();
    }

    public ThreeWayLampTests() {
        super("ThreeWayLamp");
    }

    @TestMethod
    public void testDefaultConstructor() {
        ThreeWayLamp lamp = new ThreeWayLamp();

        testEqual(lamp.getState(), LampState.Off, "Default constructor");
    }

    @TestMethod
    public void testConstructor() {
        ThreeWayLamp lamp = new ThreeWayLamp(LampState.Medium);

        testEqual(lamp.getState(), LampState.Medium, "Constructor");
    }

    @TestMethod
    public void testSetState() {
        ThreeWayLamp lamp = new ThreeWayLamp();

        lamp.setState(LampState.High);
        testEqual(lamp.getState(), LampState.High, "setState 1");
        lamp.setState(LampState.Low);
        testEqual(lamp.getState(), LampState.Low, "setState 2");
    }

    @TestMethod
    public void testIsOnState() {
        ThreeWayLamp lamp = new ThreeWayLamp();

        lamp.setState(LampState.High);
        testTrue(lamp.isOnState(LampState.High), "isOnState 1");
        lamp.setState(LampState.Low);
        testFalse(lamp.isOnState(LampState.Off), "isOnState 2");
    }

    @TestMethod
    public void testNextState() {
        ThreeWayLamp lamp = new ThreeWayLamp();

        lamp.nextState();
        testEqual(lamp.getState(), LampState.Low, "nextState 1");
        lamp.nextState();
        testEqual(lamp.getState(), LampState.Medium, "nextState 2");
        lamp.nextState();
        testEqual(lamp.getState(), LampState.High, "nextState 3");
        lamp.nextState();
        testEqual(lamp.getState(), LampState.Off, "nextState 4");
    }

    @TestMethod
    public void testPrevState() {
        ThreeWayLamp lamp = new ThreeWayLamp();

        lamp.prevState();
        testEqual(lamp.getState(), LampState.High, "prevState 1");
        lamp.prevState();
        testEqual(lamp.getState(), LampState.Medium, "prevState 2");
        lamp.prevState();
        testEqual(lamp.getState(), LampState.Low, "prevState 3");
        lamp.prevState();
        testEqual(lamp.getState(), LampState.Off, "prevState 4");
    }
}
