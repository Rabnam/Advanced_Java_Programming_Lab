package SocketProgramming;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler extends Thread {   // <-- extend Thread
    private DataInputStream in;

    public ServerHandler(Socket socket) throws IOException {
        this.in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            String msg;
            while (!(msg = in.readUTF()).equalsIgnoreCase("exit")) {
                System.out.println("Client: " + msg);
            }
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }
}

