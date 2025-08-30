package SocketProgramming;
import java.io.DataInputStream;
import java.io.IOException;

// Handles messages received from the server
public class ClientHandler extends Thread {
    private DataInputStream in;

    public ClientHandler(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String msg;
            while (!(msg = in.readUTF()).equalsIgnoreCase("exit")) {
                System.out.println("Server: " + msg);
            }
            System.out.println("Server disconnected.");
        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }
}
