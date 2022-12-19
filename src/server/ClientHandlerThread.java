package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandlerThread implements Runnable {
    Socket connectionSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public ClientHandlerThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
        try {
            this.ois = new ObjectInputStream(connectionSocket.getInputStream());
            this.oos = new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String message = "";
        while (!message.equals("stop")){
            try {
                message = (String) ois.readObject();
                System.out.println("Message Received: " + message);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
