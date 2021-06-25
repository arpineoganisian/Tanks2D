package edu.school21.game;

import edu.school21.constants.Colors;
import edu.school21.constants.Side;
import edu.school21.gamelogic.ActionManager;
import edu.school21.models.*;
import edu.school21.request.Request;
import edu.school21.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;

@Component
@Scope("prototype")
public class Game extends Thread{
    private Player player1, player2;
    private LinkedList<Bullet> bullets1 = new LinkedList<>();
    private LinkedList<Bullet> bullets2 = new LinkedList<>();
    private boolean started = false;
    private BufferedReader in1;
    private BufferedReader in2;
    private BufferedWriter out1;
    private BufferedWriter out2;
    private final ApplicationContext context = Context.getContext();
    @Autowired
    private ArenaConfig arenaConfig;
    @Autowired
    private Tank tank1, tank2;


    @Autowired
    public Game(Player player1, Player player2) {
        System.out.println("Players: " + player1.getSocket() + " " + player2.getSocket());
        this.player1 = player1;
        this.player2 = player2;
        System.out.println("Game session was created with " + this.player1.getSocket() + " " + this.player2.getSocket());
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private void initStreams() throws IOException {
        in1 = new BufferedReader(new InputStreamReader(player1.getSocket().getInputStream()));
        in2 = new BufferedReader(new InputStreamReader(player2.getSocket().getInputStream()));
        out1 = new BufferedWriter(new OutputStreamWriter(player1.getSocket().getOutputStream()));
        out2 = new BufferedWriter(new OutputStreamWriter(player2.getSocket().getOutputStream()));
    }

    private void initDefaultBullets() {
        bullets1.add(new Bullet(Side.UP, new Point(arenaConfig.getArenaHeight() + 10, arenaConfig.getArenaWidth() + 10)));
        bullets2.add(new Bullet(Side.DOWN, new Point(-10, -10)));

    }

    @Override
    public void run() {
        try {
            initStreams();
        } catch (IOException e) {
            System.err.println("Streams in/out: initialization failed");;
        }
        started = true;
        System.out.println(Colors.ANSI_CYAN + "Let's fight begin!" + Colors.ANSI_RESET);
        tank1.setSide(Side.UP);
        tank2.setSide(Side.DOWN);
        while (tank1.getHp() > 0 && tank2.getHp() > 0) {
            Response.sendResponse(out1, tank1 , tank2, bullets1, bullets2, true);
            Response.sendResponse(out2, tank2, tank1, bullets2, bullets1, false);
            ActionManager.moveTanks(bullets1, tank1, Request.getAction(in1));
            ActionManager.moveTanks(bullets2, tank2, Request.getAction(in2));
            ActionManager.moveBullets(bullets1, tank2);
            ActionManager.moveBullets(bullets2, tank1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.err.println(Colors.ANSI_RED + "Impossible to pause the execution of current thread" + Colors.ANSI_RESET);
            }
        }
        started = false;
    }

    public boolean isStarted() {
        return started;
    }
}
