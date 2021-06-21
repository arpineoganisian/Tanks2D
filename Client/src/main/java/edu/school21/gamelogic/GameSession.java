package edu.school21.gamelogic;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import edu.school21.models.Bullet;
import edu.school21.models.Tank;
import org.json.JSONObject;

public class GameSession extends Thread {
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Socket socket;
    private static String server;
    private static int port;
    private static GameSession instance;
    private static Tank playerTank = new Tank(new Point(50, 50));
    private static Tank enemyTank = new Tank(new Point(100, 100));
    private static LinkedList<Bullet> myBullets = new LinkedList<>();
    private static LinkedList<Bullet> enemyBullets = new LinkedList<>();
    private static String response = "NONE";

    public void initSession() {
        try {
            socket = new Socket(server, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.err.println("No connection to server");;
        }
        System.out.println("Connection is established");
    }

    public GameSession() {}

    public static void setServer(String server) {
        GameSession.server = server;
    }

    public static void setPort(int port) {
        GameSession.port = port;
    }

    public static GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public static Tank getPlayerTank() {
        return playerTank;
    }

    public static Tank getEnemyTank() {
        return enemyTank;
    }

    public static void setResponse(String response) {
        GameSession.response = response;
    }

    public static LinkedList<Bullet> getMyBullets() {
        return myBullets;
    }

    public static LinkedList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    @Override
    public void run() {

        JSONObject answerJson;
        JSONObject myJson;
        JSONObject enemyJson;
        JSONObject myBulletsJson;
        JSONObject myBulletJson;
        JSONObject enemyBulletsJson;
        JSONObject enemyBulletJson;

        initSession();

        String answer = "";

        try {
            if (in.readLine().equals("Start\n")) {
                System.out.println("Here we go");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                answer = in.readLine();
                System.out.println("Answer: " + answer);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Can't take response from server");
            }
            try {
                System.out.println("Response:" + response);
                out.write(response);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Can't send answer to server");
            }
            System.out.println("Answer from server: " + answer);

            if (answer != null) {
                answerJson = new JSONObject(answer);

                myJson = new JSONObject(answerJson.get("player").toString());
                playerTank.setPosition(new Point(myJson.getInt("x"), myJson.getInt("y")));
                playerTank.setHp(myJson.getInt("hp"));
                playerTank.setImage("player.png");

                enemyJson = new JSONObject(answerJson.get("enemy").toString());
                enemyTank.setPosition(new Point(enemyJson.getInt("x"), enemyJson.getInt("y")));
                enemyTank.setHp(enemyJson.getInt("hp"));
                enemyTank.setImage("enemy.png");

                myBullets.clear();
                myBulletsJson = new JSONObject(answerJson.get("playerBullets").toString());
                for (int i = 0; i < myBulletsJson.length(); i++) {
                    myBulletJson = new JSONObject(myBulletsJson.get(Integer.toString(i)).toString());
                    myBullets.add(new Bullet(new Point(myBulletJson.getInt("x"), myBulletJson.getInt("y"))));
                }
                enemyBullets.clear();
                enemyBulletsJson = new JSONObject(answerJson.get("enemyBullets").toString());
                for (int i = 0; i < enemyBulletsJson.length(); i++) {
                    enemyBulletJson = new JSONObject(enemyBulletsJson.get(Integer.toString(i)).toString());
                    enemyBullets.add(new Bullet(new Point(enemyBulletJson.getInt("x"), enemyBulletJson.getInt("y"))));
                }
            }
            else {
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
