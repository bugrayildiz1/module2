package ss.week3.pw;

public class Password {
    private final Checker checker;
    private final String initPass;
    private String password;

    public Password() {
        this(new BasicChecker());
    }

    public Password(Checker argChecker) {
        this.checker = argChecker;
        this.initPass = argChecker.generatePass();
        this.password = this.initPass;
    }

    public boolean acceptable(String suggestion) {
        return checker.acceptable(suggestion);
    }

    public boolean setWord(String oldPass, String newPass) {
        if (!testWord(oldPass) || !acceptable(newPass)) {
            return false;
        }

        password = newPass;

        return true;
    }

    /*@ pure */public boolean testWord(String word) {
        return word.equals(password);
    }

    public Checker getChecker() {
        return checker;
    }

    public String getInitPass() {
        return initPass;
    }
}
