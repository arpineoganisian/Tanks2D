package edu.school21.room;

import edu.school21.constants.Colors;
import edu.school21.game.Game;
import edu.school21.models.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.net.Socket;

@Component
@Scope("prototype")
public class Room extends Thread{
    private Socket player1Socket, player2Socket;
    private boolean player1Connected = false, player2Connected = false;
    private final ApplicationContext context = Context.getContext();
    private Game game;

    public Room() {}

    @Autowired
    private void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer1Socket(Socket player1Socket) {
        this.player1Socket = player1Socket;
        player1Connected = true;
        System.out.println(Colors.ANSI_CYAN + "Player-1 joins the room" + Colors.ANSI_RESET);
    }

    public void setPlayer2Socket(Socket player2Socket) {
        this.player2Socket = player2Socket;
        player2Connected = true;
        System.out.println(Colors.ANSI_CYAN + "Player-2 joins the room " + Colors.ANSI_RESET);
    }

    public boolean isEmpty() {
        return !player1Connected || !player2Connected;
    }

    public boolean isPlayer1Connected() {
        return player1Connected;
    }

    public boolean isPlayer2Connected() {
        return player2Connected;
    }

    @Override
    public void run() {
        game.getPlayer1().setSocket(player1Socket);
        game.getPlayer2().setSocket(player2Socket);
        game.start();
        try {
            game.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean gameIsStarted() {
        return game != null && game.isStarted();
    }

}

