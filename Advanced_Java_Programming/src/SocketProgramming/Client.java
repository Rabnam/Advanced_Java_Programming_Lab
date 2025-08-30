package SocketProgramming;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int PORT = 5000;

        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("Connected to server: " + SERVER_IP);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Start client handler to listen to server messages
            new ClientHandler(in).start();

            // Main thread sends messages to server
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while (!(msg = consoleReader.readLine()).equalsIgnoreCase("exit")) {
                out.writeUTF(msg);
                out.flush();
            }

            System.out.println("Client exiting...");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
