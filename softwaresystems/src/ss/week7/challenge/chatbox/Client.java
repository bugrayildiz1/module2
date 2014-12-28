package ss.week7.challenge.chatbox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * P2 prac wk4. <br>
 * Client.
 * @author Theo Ruys
 * @version 2005.02.21
 */
public class Client extends Thread {

    private String clientName;
    private MessageUI mui;
    private Socket sock;
    private BufferedReader in;
    private BufferedWriter out;
    private boolean connected;

    /**
     * Constructs a Client-object and tries to make a socket connection.
     */
    public Client(String name, InetAddress host, int port, MessageUI muiArg) throws IOException {
        this.clientName = name;
        this.mui = muiArg;
        this.sock = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        this.connected = true;

        // Send the client name as the first message.
        sendMessage(clientName);
    }

    /**
     * Reads the messages in the socket connection. Each message will be forwarded to the MessageUI
     */
    public void run() {
        while (connected) {
            try {
                String input = in.readLine();

                if (input == null) {
                    shutdown();
                } else {
                    mui.addMessage(input);
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    /** send a message to a ClientHandler. */
    public void sendMessage(String msg) {
        try {
            out.write(msg);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            shutdown();
        }
    }

    /** close the socket connection. */
    public void shutdown() {
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connected = false;
    }

    /** returns the client name. */
    public String getClientName() {
        return clientName;
    }

} // end of class Client
