package ss.week6.dictionaryattack;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.codec.binary.Hex;

public class DictionaryAttack {
    private Map<String, String> passwordMap;
    private Map<String, String> hashDictionary;

    /**
     * Reads a password file. Each line of the password file has the following form: username:
     * encodedpassword
     * 
     * After calling this method, the passwordMap class variable should be filled with the content
     * of the file. The key for the map should be the username and the password hash should be the
     * content.
     * @param filename
     */
    public void readPasswords(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            passwordMap = new HashMap<String, String>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(": ")) {
                    String username = line.substring(0, line.indexOf(": "));
                    String password = line.substring(username.length() + 2);

                    passwordMap.put(username, password);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a password, return the MD5 hash of a password. The resulting hash (or sometimes called
     * digest) should be hex-encoded in a String.
     * @param password
     * @return
     */
    public String getPasswordHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(password.getBytes());

            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Check the password for the user the password list. If the user does not exist, return false.
     * @param user
     * @param password
     * @return whether the password for that user was correct.
     */
    public boolean checkPassword(String user, String password) {
        if (!passwordMap.containsKey(user)) {
            return false;
        }

        return getPasswordHash(password).equals(passwordMap.get(user));
    }

    /**
     * Reads a dictionary from file (one line per word) and use it to add entries to a dictionary
     * that maps password hashes (hex-encoded) to the original password.
     * @param filename filename of the dictionary.
     */
    public void addToHashDictionary(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            hashDictionary = new HashMap<String, String>();

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();

                hashDictionary.put(getPasswordHash(word), word);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Do the dictionary attack.
     */
    public void doDictionaryAttack() {
        for (Entry<String, String> user : passwordMap.entrySet()) {
            if (hashDictionary.containsKey(user.getValue())) {
                System.out.printf("%s: %s\n", user.getKey(), hashDictionary.get(user.getValue()));
            }
        }
    }

    public static void main(String[] args) {
        DictionaryAttack da = new DictionaryAttack();

        da.readPasswords("bigsimplecorp_pw_hashes.txt");
        da.addToHashDictionary("10k-common-passwords.txt");

        da.doDictionaryAttack();

        // P-6.29
        // Assuming all strings are valid:
        // Possible combinations = 26^4
        // Password will be found on average of (26^4)/2 attempts.
    }

}
