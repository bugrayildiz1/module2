package ss.week7.cmdline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Peer for a simple client-server application.
 * @author Theo Ruys
 * @version 2005.02.21
 */
public class Peer implements Runnable {
    public static final String EXIT = "exit";

    protected String name;
    protected Socket sock;
    protected BufferedReader in;
    protected BufferedWriter out;
    protected boolean closed = false;

    /*@
       requires (nameArg != null) && (sockArg != null);
     */
    /**
     * Constructor. creates a peer object based in the given parameters.
     * @param nameArg name of the Peer-proces
     * @param sockArg Socket of the Peer-proces
     */
    public Peer(String nameArg, Socket sockArg) throws IOException {
        this.name = nameArg;
        this.sock = sockArg;
        this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
    }

    /**
     * Reads strings of the stream of the socket-connection and writes the characters to the default
     * output.
     */
    public void run() {
        while (true) {
            try {
                String line = in.readLine();

                // If line is null it indicates the end of the stream has been reached.
                if (line == null) {
                    closed = true;
                    break;
                }

                System.out.println(line);
            } catch (IOException e) {
                break;
            }
        }
    }

    /**
     * Reads a string from the console and sends this string over the socket-connection to the Peer
     * proces. On Peer.EXIT the method ends
     */
    public void handleTerminalInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!closed) {
            try {
                String line = reader.readLine();

                if (line.equals(Peer.EXIT)) {
                    break;
                }

                out.write(line);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                break;
            }
        }
    }

    /**
     * Closes the connection, the sockets will be terminated.
     */
    public void shutDown() {
        // Will also close the input/output streams.
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** returns name of the peer object. */
    public String getName() {
        return name;
    }

    /** read a line from the default input. */
    static public String readString(String tekst) {
        System.out.print(tekst);
        String antw = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            antw = in.readLine();
        } catch (IOException e) {
            // Fixes the 'Must have at least one statement' Checkstyle warning.
            e.printStackTrace();
        }

        return (antw == null) ? "" : antw;
    }
}
