package edu.school21.server;

import edu.school21.session.game.GameSession;
import edu.school21.session.room.SessionRoom;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {
    private Thread session;
    private ServerSocket serverSocket;
    private Socket player1Socket;
    private Socket player2Socket;
    private int port;

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
            while (true) {
                SessionRoom.getRoom();
                SessionRoom.getRoom().setPlayerSocket(player1Socket, SessionRoom.Player.FIRST);
                SessionRoom.getRoom().setPlayerSocket(player2Socket, SessionRoom.Player.SECOND);
                if (!SessionRoom.isStarted()) {
                    SessionRoom.getRoom().startGame();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
