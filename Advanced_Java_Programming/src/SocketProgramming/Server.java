package SocketProgramming;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int PORT = 5000;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            // Start server handler thread to listen to client messages
            new ServerHandler(clientSocket).start();

            // Main thread sends messages to client
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while (!(msg = consoleReader.readLine()).equalsIgnoreCase("exit")) {
                out.writeUTF(msg);
                out.flush();
            }

            System.out.println("Server exiting...");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
