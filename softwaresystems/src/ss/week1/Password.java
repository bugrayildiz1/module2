package ss.week1;

public class Password {
    public static final String INITIAL = "";

    private String password;

    public Password() {
        this.password = INITIAL;
    }

    public boolean acceptable(String suggestion) {
        if (suggestion == null) {
            // Suggestion cannot be null.
            return false;
        }
        if (suggestion.length() < 6) {
            // Suggestion must have at least 6 characters.
            return false;
        }
        if (suggestion.contains(" ")) {
            // Suggestion cannot contain any spaces.
            return false;
        }

        // Acceptable suggestion.
        return true;
    }

    public boolean setWord(String oldPass, String newPass) {
        if (!testWord(oldPass)) {
            // Old password is invalid.
            return false;
        }
        if (!acceptable(newPass)) {
            // New password is not acceptable.
            return false;
        }

        // Update the password to the new password.
        password = newPass;

        return true;
    }

    public boolean testWord(String word) {
        return word.equals(password);
    }
}
