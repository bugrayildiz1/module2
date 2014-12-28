package ss.week7.challenge.chatbox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;

/**
 * P2 prac wk5. <br>
 * Server. A Thread class that listens to a socket connection on a specified port. For every socket
 * connection with a Client, a new ClientHandler thread is started.
 * @author Theo Ruys
 * @version 2005.02.21
 */
public class Server extends Thread {
    private int port;
    private MessageUI mui;
    private Collection<ClientHandler> threads;

    /** Constructs a new Server object. */
    public Server(int portArg, MessageUI muiArg) {
        this.port = portArg;
        this.mui = muiArg;
        this.threads = new LinkedList<ClientHandler>();
    }

    /**
     * Listens to a port of this Server if there are any Clients that would like to connect. For
     * every new socket connection a new ClientHandler thread is started that takes care of the
     * further communication with the Client.
     */
    public void run() {
        try {
            @SuppressWarnings("resource")
            ServerSocket listenSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = listenSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, clientSocket);

                addHandler(clientHandler);

                clientHandler.announce();
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message using the collection of connected ClientHandlers to all connected Clients.
     * @param msg message that is send
     */
    // This method is problematic as iterating over a collection isn't thread safe. If addHandler
    // or removeHandler is called while iterating it would throw a ConcurentModificationException.
    // Declaring this method as synchronized alone wouldn't solve this issue though. The addHandler
    // and removeHandler methods would also require some form of synchronization to properly thread
    // safe the code.
    public void broadcast(String msg) {
        for (ClientHandler handler : threads) {
            handler.sendMessage(msg);
        }

        mui.addMessage(msg);
    }

    /**
     * Add a ClientHandler to the collection of ClientHandlers.
     * @param handler ClientHandler that will be added
     */
    public void addHandler(ClientHandler handler) {
        threads.add(handler);
    }

    /**
     * Remove a ClientHandler from the collection of ClientHanlders.
     * @param handler ClientHandler that will be removed
     */
    public void removeHandler(ClientHandler handler) {
        threads.remove(handler);
    }

} // end of class Server
