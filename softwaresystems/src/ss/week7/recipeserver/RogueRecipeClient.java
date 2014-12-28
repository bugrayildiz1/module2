package ss.week7.recipeserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * A client for a sloppily programmed recipe server.
 *
 */
public class RogueRecipeClient {
    private static final String USAGE = "usage: <address> <port>";

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(0);
        }

        InetAddress addr = null;
        int port = 0;
        Socket sock = null;

        // check args[1] - the IP-adress
        try {
            addr = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: host " + args[0] + " unknown");
            System.exit(0);
        }

        // parse args[2] - the port
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: port " + args[1] + " is not an integer");
            System.exit(0);
        }

        // try to open a Socket to the server
        try {
            sock = new Socket(addr, port);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a socket on " + addr + " and port " + port);
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            Scanner userIn = new Scanner(System.in);
            String input;

            while (true) {
                // Requesting the following recipe will spit out the server code.
                // ../src/ss/week7/recipeserver/RecipeServer.java
                // The issue is that paths aren't validated properly and a rogue client can use the
                // .. path to move up a directory, which then gives access to the root directory of
                // the repo, which in turn gives access to the server's source file.
                input = userIn.nextLine();

                if (input.equals("exit")) {
                    break;
                } else {
                    out.write("GET " + input);
                    out.newLine();
                    out.flush();
                    System.out.println("Recipe text:");
                    System.out.println("------");
                    String line = in.readLine();
                    while (line != null && !line.equals("--EOT--")) {
                        // The server uses a special string ("--EOT--") to mark the end of a recipe.
                        System.out.println(line);
                        line = in.readLine();
                    }
                    System.out.println("------");
                }
            }
            System.out.println("Exiting.");
            userIn.close();
        } catch (IOException e) {
            System.out.println("ERROR: unable to communicate to server");
            e.printStackTrace();
        }
    }
}
