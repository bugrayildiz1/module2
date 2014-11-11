package ss.week1;

public class ThreeWayLamp {
    private LampState state;

    public ThreeWayLamp() {
        this.state = LampState.Off;
    }

    public LampState getState() {
        return state;
    }

    public void setState(LampState argState) {
        state = argState;
    }
}
