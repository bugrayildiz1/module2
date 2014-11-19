package ss.week6.cards;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Card implements Serializable {

    // ---- constants -----------------------------------
    private static final long serialVersionUID = 2730594976152737018L;
    // ranks are 2, ..., 9 and:
    public static final char JACK = 'J';
    public static final char QUEEN = 'Q';
    public static final char KING = 'K';
    public static final char ACE = 'A';
    public static final char TEN = 'T';

    // suits are:
    public static final char CLUBS = 'C';
    public static final char DIAMONDS = 'D';
    public static final char HEARTS = 'H';
    public static final char SPADES = 'S';

    // some convenient arrays
    private static final char[] RANK_CHARACTERS = "23456789TJQKA".toCharArray();
    private static final char[] SUIT_CHARACTERS = {'C', 'D', 'H', 'S'};
    private static final String[] RANK_STRINGS = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
        "jack", "queen", "king", "ace"};
    private static final String[] SUIT_STRINGS = {"Clubs", "Diamonds", "Hearts", "Spades"};

    // ---- class methods -------------------------------------

    /**
     * Translates a char encoding of rank into it's String representation.
     * @return the String representation of rank
     * @param rank the char encoding a rank
     * @return null if <code>isValidRank(rank)</code> returns <code>false</code>
     */
    private static String rankChar2String(char rank) {
        int i;
        for (i = 0; i < 13 && RANK_CHARACTERS[i] != rank; i++) {
            // Useless statement to prevent Checkstyle from whining about an empty statement.
            i = i + 0;
        }
        return (i == 13) ? null : RANK_STRINGS[i];
    }

    /**
     * Translates a suit encoding of rank into its String representation.
     * @return the String representation of suit
     * @param rank the char encoding a suit
     * @return null if <code>isValidSuit(suit)</code> returns <code>false</code>
     */
    private static String suitChar2String(char suit) {
        int i;
        for (i = 0; i < 4 && SUIT_CHARACTERS[i] != suit; i++) {
            // Useless statement to prevent Checkstyle from whining about an empty statement.
            i = i + 0;
        }
        return (i == 4) ? null : SUIT_STRINGS[i];
    }

    /**
     * Translates a String encoding of rank into its character representation.
     * @param rank the String encoding a rank
     * @return the char encoding of rank
     * @return '?' if <code>isValidRank(rank)</code> returns <code>false</code>
     */
    private static char rankString2Char(String rank) {
        int i;
        for (i = 0; i < 13 && !(RANK_STRINGS[i].equals(rank)); i++) {
            // Useless statement to prevent Checkstyle from whining about an empty statement.
            i = i + 0;
        }
        return (i == 13) ? '?' : RANK_CHARACTERS[i];
    }

    /**
     * Translates a suit String into its character encoding.
     * @param rank the String representation of a suit
     * @return the character encoding of suit
     * @return '?' if <code>isValidSuit(suit)</code> returns <code>false</code>
     */
    private static char suitString2Char(String suit) {
        int i;
        for (i = 0; i < 4 && !(SUIT_STRINGS[i].equals(suit)); i++) {
            // Useless statement to prevent Checkstyle from whining about an empty statement.
            i = i + 0;
        }
        return (i == 4) ? '?' : SUIT_CHARACTERS[i];
    }

    /**
     * Tests if a <tt>char</tt> represents a valid suit.
     * @return <tt>true</tt> if <tt>k</tt> in <tt>CLUBS | DIAMONDS | HEARTS | SPADES</tt>
     */
    /*@pure*/
    public static boolean isValidSuit(char suit) {
        return suit == CLUBS || suit == DIAMONDS || suit == HEARTS || suit == SPADES;
    }

    /**
     * Tests if a <tt>char</tt> represents a valid rank.
     * @return <tt>true</tt> if <tt>k</tt> in <tt>'2'..'9' | TEN | JACK | QUEEN | KING | ACE</tt>
     */
    /*@pure*/
    public static boolean isValidRank(char r) {
        return ('2' <= r && r <= '9') || r == TEN || r == JACK || r == QUEEN || r == KING
                || r == ACE;
    }

    /*@
       requires isValidSuit(s1) && isValidSuit(s2);
     */
    /**
     * Tests if a suit is value of a suit is less than the value of another suit following the order
     * CLUBS < DIAMONDS < HEARTS < SPADES.
     */
    public static boolean suitLessThan(char s1, char s2) {
        boolean result = false;
        if (s1 == CLUBS) {
            result = s2 != CLUBS;
        } else if (s1 == DIAMONDS) {
            result = s2 != CLUBS && s2 != DIAMONDS;
        } else if (s1 == HEARTS) {
            result = s2 == SPADES;
        }
        return result;
    }

    /*@
       requires isValidRank(r1) && isValidRank(r2);
     */
    /**
     * Tests if one rank is less then the other following the order '2' < '3' < '4' < '5' <... < TEN
     * < JACK < QUEEN < KING < ACE.
     */
    public static boolean rankLessThan(char r1, char r2) {
        int i;
        for (i = 0; RANK_CHARACTERS[i] != r1 && RANK_CHARACTERS[i] != r2; i++) {
            // Useless statement to prevent Checkstyle from whining about an empty statement.
            i = i + 0;
        }
        return RANK_CHARACTERS[i] == r2 ? false : RANK_CHARACTERS[i] == r1;
    }

    /*@
       requires isValidRank(r1) && isValidRank(r2);
     */
    /**
     * Tests if one rank directly follows the other accroding to '2' < '3' < '4' < ... < TEN < JACK
     * < QUEEN < KING < ACE.
     */
    public static boolean isRankFollowing(char r1, char r2) {
        boolean result = false;
        if (r1 == KING) {
            result = r2 == ACE;
        } else if (r1 == QUEEN) {
            result = r2 == KING;
        } else if (r1 == JACK) {
            result = r2 == QUEEN;
        } else if (r1 == TEN) {
            result = r2 == JACK;
        } else if (r1 == '9') {
            result = r2 == TEN;
        } else {
            result = r2 == r1 + 1;
        }
        return result;
    }

    // ---- instance variabeles -----------------------------------

    /*@
       private invariant isValidSuit(suit);
     */
    /**
     * Suit of this card.
     */
    private char suit;
    /*@
       private invariant isValidRank(rank);
     */
    /**
     * Rank of this card..
     */
    private char rank;

    // ---- constructors -----------------------------------

    /*@
       requires isValidSuit(s) && isValidRank(r);
     */
    /**
     * Creates a new Card with the given suit and rank.
     * @param s suit of the new card.
     * @param r rank of the new card.
     */
    public Card(char s, char r) {
        assert isValidSuit(s) && isValidRank(r);
        suit = s;
        rank = r;
    }

    // ---- queries ---------------------------------------

    /*@
       ensures isValidSuit(\result);
     */
    /**
     * Returns the suit of this card.
     * @return suit of this card.
     */
    public char getSuit() {
        return suit;
    }

    /*@
       ensures isValidRank(\result);
    */
    /**
     * Returns the rank of this card.
     * @return rank of this card.
     */
    public char getRank() {
        return rank;
    }

    /** Returns a String description of this card. */
    public String toString() {
        String res;

        switch (getSuit()) {
            case CLUBS:
                res = "Clubs";
                break;
            case DIAMONDS:
                res = "Diamonds";
                break;
            case HEARTS:
                res = "Hearts";
                break;
            default:
                res = "Spades";
        }
        res += " ";
        switch (getRank()) {
            case TEN:
                res += "10";
                break;
            case JACK:
                res += "jack";
                break;
            case QUEEN:
                res += "queen";
                break;
            case KING:
                res += "king";
                break;
            case ACE:
                res += "ace";
                break;
            default:
                res += getRank();
        }

        return res;
    }

    /**
     * Tests if this card is equal to another (i.e., same suit and rank).
     * @param obj Card to be compared.
     * @return <code>true</code> if the suit and rank of <code>obj</code> are the same as of this
     * Card.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }
        Card card = (Card) obj;
        return this.getSuit() == card.getSuit() && this.getRank() == card.getRank();
    }

    /**
     * Overwrites the hashcode from Object.
     */
    public int hashCode() {
        return 100 * rank + suit;
    }

    /*@
       requires card != null;
     */
    /**
     * Tests if this Card is less in suit than another Card.
     * @see #suitLessThan(char, char)
     * @param card Card for the comparison.
     * @return <code>true</code> if the suit of this Card is less than that of <code>card</code>.
     */
    public boolean ltSuit(Card card) {
        return suitLessThan(this.getSuit(), card.getSuit());
    }

    /*@
       requires card != null;
     */
    /**
     * Tests if this Card is less in rank that another Card.
     * @see #rankLessThan(char, char)
     * @param card Card for the comparison
     * @return <code>true</code> if the rank of this Card is less than that of <code>kaart</code>.
     */
    public boolean ltRank(Card card) {
        return rankLessThan(this.getRank(), card.getRank());
    }

    /*@
       requires card != null;
     */
    /**
     * Tests if this Card is directly followed in rank by another Card. Does not consider suit. see
     * {@link #isRankFollowing(char, char)}
     * @param card Card for the comparison.
     * @return <code>true</code> if the rank of this Card directly precedes the rank of
     * <code>card</code>.
     */
    public boolean isInRankBefore(Card card) {
        return isRankFollowing(this.getRank(), card.getRank());
    }

    public void write(PrintWriter writer) {
        writer.println(this.toString());
    }

    public void write(DataOutputStream output) throws IOException {
        output.writeChar(suit);
        output.writeChar(rank);
    }

    public void write(ObjectOutputStream output) throws IOException {
        output.writeObject(this);

    }

    public static Card read(BufferedReader in) throws EOFException {
        String line = null;
        String suit = null;
        String rank = null;
        Card card = null;

        try {
            line = in.readLine();
        } catch (IOException e1) {
            return null;
        }

        if (line == null) {
            throw new EOFException("Unexpected end of file");
        }

        Scanner scanner = new Scanner(line);

        try {
            suit = scanner.next();
            rank = scanner.next();
        } catch (NoSuchElementException e) {
            // Not needed, but needed a statement to prevent Checkstyle from whining about an empty
            // statement.
            suit = null;
            rank = null;
        } finally {
            scanner.close();
        }

        if (suit != null && rank != null) {
            char suitChar = suitString2Char(suit);
            char rankChar = rankString2Char(rank);

            if (isValidSuit(suitChar) && isValidRank(rankChar)) {
                card = new Card(suitChar, rankChar);
            }
        }

        return card;
    }

    public static Card read(DataInputStream input) throws EOFException {
        try {
            char suitChar = input.readChar();
            char rankChar = input.readChar();

            if (isValidSuit(suitChar) && isValidRank(rankChar)) {
                return new Card(suitChar, rankChar);
            } else {
                return null;
            }
        } catch (EOFException e) {
            throw e;
        } catch (IOException e) {
            return null;
        }
    }

    public static Card read(ObjectInputStream input) {
        try {
            return (Card) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        PrintWriter writer;

        if (args.length != 0) {
            try {
                File file = new File(args[0]);
                writer = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                System.err.println("Failed to create PrintWriter: " + e.getMessage());
                return;
            }
        } else {
            writer = new PrintWriter(System.out);
        }

        Card card1 = new Card(DIAMONDS, JACK);
        Card card2 = new Card(CLUBS, QUEEN);
        Card card3 = new Card(SPADES, '4');
        Card card4 = new Card(HEARTS, ACE);

        card1.write(writer);
        card2.write(writer);
        card3.write(writer);
        card4.write(writer);

        writer.flush();
        writer.close();
    }
}
