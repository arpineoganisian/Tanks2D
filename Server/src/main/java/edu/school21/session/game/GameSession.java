package edu.school21.session.game;

import edu.school21.constants.Action;
import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import edu.school21.logic.ActionManager;
import edu.school21.message.Request;
import edu.school21.message.Response;
import edu.school21.models.Tank;
import edu.school21.models.Bullet;
import edu.school21.session.room.SessionRoom;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class GameSession extends Thread{
    private LinkedList<Bullet> player1Bullet;
    private LinkedList<Bullet> player2Bullet;
    private Tank tank1, tank2;
    private BufferedReader player1In;
    private BufferedReader player2In;
    private BufferedWriter player1Out;
    private BufferedWriter player2Out;
    private Socket player1Socket, player2Socket;
    private static GameSession instance;

    public static GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public void setPlayer1Socket(Socket player1Socket) {
        this.player1Socket = player1Socket;
    }

    public void setPlayer2Socket(Socket player2Socket) {
        this.player2Socket = player2Socket;
    }

    private void initGame() {
        try {
            player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            player1Out = new BufferedWriter(new OutputStreamWriter(player1Socket.getOutputStream()));
            player2Out = new BufferedWriter(new OutputStreamWriter(player2Socket.getOutputStream()));
            System.out.println("Let's fight begin!");
            tank1 = new Tank(new Point(Constants.startPosition1.x, Constants.startPosition1.y), Side.DOWN);
            System.out.println(tank1);
            tank2 = new Tank(new Point(Constants.startPosition2.x, Constants.startPosition2.y), Side.UP);
            System.out.println(tank2);
            player1Bullet = new LinkedList<>();
            player1Bullet.addLast(new Bullet(new Point(Constants.arenaWidth + 10, Constants.arenaHeight + 10), Side.UP));
            player2Bullet = new LinkedList<>();
            player2Bullet.addLast(new Bullet(new Point(Constants.arenaWidth + 10, Constants.arenaHeight + 10), Side.UP));
        } catch (IOException e) { e.printStackTrace(); }
        System.out.println("Initialization of game session variables");
    }

    private String getWinnner() {
        if (tank1.getHp() > 0)
            return "Player 1";
        return "Player 2";
    }

    @Override
    public void run() {
        initGame();
        try {
            player1Out.write("Start\n");
            player1Out.flush();
            player2Out.write("Start\n");
            player2Out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ActionManager.moveTanks(player1Bullet, tank1, Action.NONE);
        ActionManager.moveTanks(player2Bullet, tank2, Action.NONE);
        ActionManager.moveBullets(player1Bullet, tank2);
        ActionManager.moveBullets(player2Bullet, tank1);
        while (tank1.getHp() > 0 && tank2.getHp() > 0) {
            Response.sendResponse(player1Out, tank1, tank2, player1Bullet, player2Bullet, false);
            Response.sendResponse(player2Out, tank2, tank1, player2Bullet, player1Bullet, true);
            ActionManager.moveTanks(player1Bullet, tank1, Request.getAction(player1In));
            ActionManager.moveTanks(player2Bullet, tank2, Request.getAction(player2In));
            ActionManager.moveBullets(player1Bullet, tank2);
            ActionManager.moveBullets(player2Bullet, tank1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.err.println("Thread can't sleep sleep");
                e.printStackTrace();
            }
        }
            System.out.println("Fight ends " + getWinnner());
    }
}
