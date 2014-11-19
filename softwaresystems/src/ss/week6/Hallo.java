package ss.week6;

import java.util.Scanner;

public class Hallo {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String input;

        do {
            System.out.println("What is your name?");

            input = in.nextLine();

            System.out.println("Hello " + input);

        } while (!input.isEmpty());
    }
}
