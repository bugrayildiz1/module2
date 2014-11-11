package ss.week1;

public class ThreeWayLamp {
    private LampState state;

    public ThreeWayLamp() {
        this.state = LampState.Off;
    }

    public LampState getState() {
        return this.state;
    }

    public void setState(LampState state) {
        this.state = state;
    }
}
