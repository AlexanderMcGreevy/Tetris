// TetrisSocketClient.java
import java.io.*;
import java.net.*;

public class TetrisSocketClient {

    public static void sendScore(String name, int score) {
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host.getHostName(), 9876);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(name + ":" + score);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String response = (String) ois.readObject();
            System.out.println("Server response: " + response);

            oos.close();
            ois.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
