package ss.week3;

public class Format {
    public static void printLine(String text, double amount) {
        System.out.println(String.format("%-20s%6.2f", text, amount));
    }
}
