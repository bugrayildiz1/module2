package ss.week3.pw;

public class TimedPassword extends Password {
    public static final long VALID_TIME = 60 * 60 * 1000; // 1 hour.

    private long validTime;

    public TimedPassword() {
        this(new BasicChecker());
    }

    public TimedPassword(Checker checker) {
        super(checker);

        this.validTime = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - validTime > VALID_TIME;
    }

    @Override
    public boolean setWord(String oldPass, String newPass) {
        boolean result = super.setWord(oldPass, newPass);

        // If the password was updated successfully reset the valid time.
        if (result) {
            validTime = System.currentTimeMillis();
        }

        return result;
    }

    // Overwriting method testPass to return false if the password expired would mean you would 
    // not be able to change the password after it expires as setWord requires testPass to succeed.
}
