package ss.week6;

import java.util.Scanner;

public class Words {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Line (or \"end\"): ");

            String line = in.nextLine();

            if (line.startsWith("end")) {
                System.out.println("End of programme.");

                break;
            }

            int word = 1;
            Scanner scanner = new Scanner(line);

            while (scanner.hasNext()) {
                System.out.println(String.format("Word %d: %s", word++, scanner.next()));
            }

            scanner.close();
        }
    }

}
