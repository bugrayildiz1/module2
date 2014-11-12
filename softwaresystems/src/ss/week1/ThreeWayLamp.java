package ss.week1;

/**
 * Models a three way lamp.
 * @author ciske
 */
public class ThreeWayLamp {
    private LampState state;

    /**
     * Creates a new three way lamp initialized to the off state.
     */
    public ThreeWayLamp() {
        this.state = LampState.Off;
    }

    /**
     * Creates a new three way lamp.
     * @param argState The initial state of the lamp.
     */
    public ThreeWayLamp(LampState argState) {
        this.state = argState;
    }

    /**
     * Set the lamp to the next (higher) state. The state will wrap from highest to off.
     */
    public void nextState() {
        int currentState = state.ordinal();
        int newState = (currentState + 1) % LampState.values().length;

        state = LampState.values()[newState];
    }

    /**
     * Set the lamp to the previous (lower) state. The state will wrap from off to highest.
     */
    public void prevState() {
        int currentState = state.ordinal();
        int newState = currentState >= 1 ? currentState - 1 : LampState.values().length - 1;

        state = LampState.values()[newState];
    }

    /**
     * Checks if the lamp is currently on the specified state.
     * @param argState The state to check the lamp is on.
     */
    public boolean isOnState(LampState argState) {
        return state == argState;
    }

    /**
     * Returns the current lamp state.
     */
    public LampState getState() {
        return state;
    }

    /**
     * Sets the lamp state.
     * @param argState The new lamp state.
     */
    public void setState(LampState argState) {
        state = argState;
    }
}
