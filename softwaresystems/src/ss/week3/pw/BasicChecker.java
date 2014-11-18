package ss.week3.pw;

public class BasicChecker implements Checker {
    public static final String GENERATED_CONSTANT = "superstrongpasswordkappa";

    @Override
    public boolean acceptable(String password) {
        return password.length() >= 6 && !password.contains(" ");
    }

    @Override
    public String generatePass() {
        return GENERATED_CONSTANT;
    }

}
