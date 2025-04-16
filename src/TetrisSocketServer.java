// TetrisSocketServer.java
import java.io.*;
import java.net.*;
import java.util.*;

public class TetrisSocketServer {

    private static ServerSocket server;
    private static final int port = 9876;

    // Store scores with names
    private static final Map<String, Integer> leaderboard = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        System.out.println("Tetris Server started on port " + port);

        while (true) {
            System.out.println("Waiting for the client request...");
            Socket socket = server.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // Expecting "name:score"
            String message = (String) ois.readObject();
            System.out.println("Message received: " + message);

            if (message.equalsIgnoreCase("exit")) {
                oos.writeObject("Server shutting down.");
                break;
            }

            String[] parts = message.split(":");
            if (parts.length == 2) {
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                leaderboard.put(name, score);
                oos.writeObject("Score received for " + name + ": " + score);
            } else {
                oos.writeObject("Invalid format. Use 'name:score'");
            }

            ois.close();
            oos.close();
            socket.close();
        }

        System.out.println("Shutting down Tetris server!");
        server.close();
    }
}
