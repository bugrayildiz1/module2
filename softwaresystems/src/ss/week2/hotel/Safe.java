package ss.week2.hotel;

/**
 * Represents a password protected safe which can be activated and opened.
 * @author ciske
 * 
 */
public class Safe {
    private final Password password;
    private boolean active;
    private boolean open;

    /**
     * Creates a new <code>Safe</code> instance which is initially deactivated and closed.
     * @param argPassword The password of this <code>Safe</code>.
     */
    //@ requires argPassword != null;
    //@ ensures getPassword() == argPassword;
    //@ ensures isActive() == false;
    //@ ensures isOpen() == false;
    public Safe(Password argPassword) {
        assert argPassword != null;

        this.password = argPassword;
        this.active = false;
        this.open = false;
    }

    /**
     * Activates the safe if the supplied password string is the correct password.
     * @param argPassword The password string. Cannot be <code>null</code>.
     */
    //@ requires argPassword != null;
    //@ ensures isActive() == getPassword().testWord(argPassword);
    public void activate(String argPassword) {
        assert argPassword != null;

        if (password.testWord(argPassword)) {
            active = true;
        }
    }

    /**
     * Deactivates and closes the safe.
     */
    //@ ensures isActive() == false && isOpen() == false;
    public void deactivate() {
        active = false;
        open = false;
    }

    /**
     * Opens the safe if it is active and the supplied password string is the correct password.
     * @param argPassword The password string. Cannot be <code>null</code>.
     */
    //@ requires argPassword != null;
    //@ ensures isOpen() == isActive() == true && getPassword().testWord(argPassword);
    public void open(String argPassword) {
        assert argPassword != null;

        if (active && password.testWord(argPassword)) {
            open = true;
        }
    }

    /**
     * Closes the safe, but does not change the activation status.
     */
    //@ ensures isOpen() == false && \old(isActive()) == isActive();
    public void close() {
        open = false;
    }

    /**
     * Returns if the safe is currently active.
     */
    /*@ pure */public boolean isActive() {
        return active;
    }

    /**
     * Returns if the safe is current open.
     */
    /*@ pure */public boolean isOpen() {
        return open;
    }

    /**
     * Returns the <code>Password</code> instance associated to this <code>Safe</code>.
     */
    //@ ensures \result != null;
    /*@ pure */public Password getPassword() {
        return password;
    }
}
