package edu.school21.server;

import edu.school21.session.GameSession;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket player1Socket;
    private Socket player2Socket;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() {
        System.out.println("Server has started!");
        try {
            player1Socket = serverSocket.accept();
            System.out.println("First player is connected");
            player2Socket = serverSocket.accept();
            System.out.println("Second player is connected");
            GameSession gameSession = GameSession.getInstance();
            gameSession.setPlayer1Socket(player1Socket);
            gameSession.setPlayer2Socket(player2Socket);
            gameSession.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
