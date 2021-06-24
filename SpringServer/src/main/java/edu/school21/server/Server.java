package edu.school21.server;

import edu.school21.config.ServerConfiguration;
import edu.school21.constants.Colors;
import edu.school21.models.Context;
import edu.school21.room.Room;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Room room;


    public Server() {
    }



    public void setPort(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println(Colors.ANSI_CYAN + "Server started");
    }

    public void start() throws IOException, InterruptedException {
        ApplicationContext context = Context.getContext();
        System.out.println(Colors.ANSI_CYAN + "Server socket: " + Colors.ANSI_RED + serverSocket + Colors.ANSI_RESET);
        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println(Colors.ANSI_CYAN + "Player connected: " + Colors.ANSI_RED + clientSocket.getInetAddress() + Colors.ANSI_RESET);
            if (room == null || (room != null && room.gameIsStarted())) {
                room = context.getBean("room", Room.class);
            }
            if (!room.isPlayer1Connected()) {
                room.setPlayer1Socket(clientSocket);
            }
            else if (!room.isPlayer2Connected()) {
                room.setPlayer2Socket(clientSocket);
            }
            if (!room.isEmpty()) {
                room.startGame();
            }
        }
    }
}
