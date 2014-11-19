package ss.week6;

public class WrongArgumentException extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongArgumentException(String message) {
        super(message);
    }
}
