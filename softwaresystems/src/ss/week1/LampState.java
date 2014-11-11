package ss.week1;

/**
 * Enumerates the possible three-way lamp states.
 * @author ciske
 *
 */
public enum LampState {
    /**
     * The off state.
     */
    Off(0),
    /**
     * The low state.
     */
    Low(1), 
    /**
     * The medium state.
     */
    Medium(2), 
    /**
     * The high state.
     */
    High(3);

    private final int state;

    private LampState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
