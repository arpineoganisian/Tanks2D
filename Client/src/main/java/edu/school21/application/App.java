package edu.school21.application;

import edu.school21.constants.Constants;
import edu.school21.gamelogic.GameSession;
import edu.school21.models.Bullet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.ArrayList;

public class App extends Application {

    private final int screenHeight = Constants.arenaHeight;
    private final int screenWidth = Constants.arenaWidth;
    private long recharge = 1000L;

    @Override
    public void start(Stage theStage) {

        //JavaFX variables initialisation:
        theStage.setTitle("Tanks!");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //getting instance of Class, that's responsible for communication with server
        GameSession gameSession = GameSession.getInstance();


        //key hook catcher:
        ArrayList<String> input = new ArrayList<>();
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

        //initialization of unmovable game objects:
        Image field = new Image("field.png",
                800, 800, false, false);
        Image playerLifeBar = new Image("life.png",
                200, 20, false, false);
        Image enemyLifeBar = new Image("life.png",
                200, 20, false, false);
        Image playerLifeBarBorder = new Image("border.png",
                204, 24, false, false);
        Image enemyLifeBarBorder = new Image("border.png",
                204, 24, false, false);

        //rendering in a "loop"
        //method handle is calling in every frame, till stop() is called
        new AnimationTimer() {
            long lastShotTime = System.currentTimeMillis();
            @Override
            public void handle(long currentNanoTime) {
                if (input.contains("LEFT")){
                    gameSession.setResponse("LEFT");
                }
                if (input.contains("RIGHT")) {
                    gameSession.setResponse("RIGHT");
                }
                if (input.contains("SPACE") && System.currentTimeMillis() - lastShotTime > recharge) {
                        lastShotTime = System.currentTimeMillis();
                        gameSession.setResponse("SHOOT");
                }
                if (!(input.contains("SPACE")) && !(input.contains("RIGHT")) && !(input.contains("LEFT"))) {
                    gameSession.setResponse("NONE");
                }

                Image playerLifeBar = new Image("life.png", gameSession.getPlayerTank().getHp()*2, 20, false, false);
                Image enemyLifeBar = new Image("life.png", gameSession.getEnemyTank().getHp()*2, 20, false, false);

                // rendering
                gc.clearRect(0, 0, Constants.arenaWidth, Constants.arenaHeight);
                gc.drawImage(field, 0, 0);
                gc.drawImage(playerLifeBar, 10, 770);
                gc.drawImage(playerLifeBarBorder, 9, 768);
                gc.drawImage(enemyLifeBar, 590, 10);
                gc.drawImage(enemyLifeBarBorder, 589, 8);
                System.out.println(GameSession.getPlayerTank().getHp() + "-----HP----- " + GameSession.getEnemyTank().getHp());
                gc.drawImage(gameSession.getPlayerTank().getImage(),
                        gameSession.getPlayerTank().getPosition().x,
                        gameSession.getPlayerTank().getPosition().y);
                gc.drawImage(gameSession.getEnemyTank().getImage(),
                        gameSession.getEnemyTank().getPosition().x,
                        gameSession.getEnemyTank().getPosition().y);

                for (Bullet bullet: gameSession.getMyBullets()) {
                    bullet.setImage("playerBullet.png");
                    gc.drawImage(bullet.getImage(),
                            bullet.getPosition().x,
                            bullet.getPosition().y);
                }

               for (Bullet bullet2: gameSession.getEnemyBullets()) {
                   bullet2.setImage("enemyBullet.png");
                   gc.drawImage(bullet2.getImage(),
                           bullet2.getPosition().x,
                           bullet2.getPosition().y);
               }
               //end of the game
               if (gameSession.getPlayerTank().getHp() <= 5 || gameSession.getEnemyTank().getHp() <= 5) {
                   gc.clearRect(0, 0, Constants.arenaWidth, Constants.arenaHeight);
                   gc.drawImage(field, 0, 0);
                   gc.drawImage(new Image("fail.png", 650, 650, false, false), 75, 150);
                   Label label = new Label();
                   root.getChildren().add(label);
                   if (gameSession.getPlayerTank().getHp() <= 5) {
                       label.setText("     YOU LOSE");
                   }
                   else {
                       label.setText("      YOU WIN");
                   }
                   label.setStyle("-fx-font-size: 100; -fx-text-fill: #6c0909");
                   stop();
                }
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println(args.length);
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        if (!args[0].equals("--server-port=" + port)) {
            throw new IllegalArgumentException("Invalid argument");
        }

        //getting instance of Class, that's responsible for communication with server
        GameSession gameSession = GameSession.getInstance();
        gameSession.setPort(port);
        gameSession.setServer("localhost");
        gameSession.start();

        //launching rendering
        launch();

        //launching communication with server
        try {
            gameSession.join();
        } catch (InterruptedException e) {
            System.err.println("Impossible to join Game Session thread");
            e.printStackTrace();
        }
    }
}
