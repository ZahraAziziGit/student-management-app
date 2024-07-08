package Server;

import java.net.*;
import java.io.*;

class Server {
    public static void main(String[] args){
        int port = 2041;
        System.out.println("-------------------Server Started-------------------");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            while (true) {
                System.out.println("Waiting for client...");
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
