package ss.week3.pw;

public class BasicChecker implements Checker {

    @Override
    public boolean acceptable(String password) {
        return password.length() >= 6 && !password.contains(" ");
    }

}
