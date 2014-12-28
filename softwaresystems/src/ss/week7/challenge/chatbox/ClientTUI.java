package ss.week7.challenge.chatbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class ClientTUI implements MessageUI {
    private Client client;

    public ClientTUI(String name, Inet4Address host, int port) {
        try {
            client = new Client(name, host, port, this);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputLoop() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String input = reader.readLine();

                if (input.equals("exit")) {
                    break;
                }

                client.sendMessage(input);
            } catch (IOException e) {
                break;
            }
        }

        client.shutdown();
    }

    @Override
    public void addMessage(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        try {
            Inet4Address address = (Inet4Address) Inet4Address.getByName("127.0.0.1");
            ClientTUI client = new ClientTUI("client2", address, 2727);
            client.inputLoop();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
