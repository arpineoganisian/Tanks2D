package edu.school21.message;

import edu.school21.models.Bullet;
import edu.school21.models.Tank;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Response {

    public static void  sendResponse(BufferedWriter out, Tank player, Tank enemy, LinkedList<Bullet> playerBullets,
                                     LinkedList<Bullet> enemyBullets, boolean reverse) {
        JSONObject response = new JSONObject();
        response.put("player", player.toJson(true));
        response.put("enemy", enemy.toJson(false));
        JSONObject playerBulletsJson = new JSONObject();
        for (Bullet bullet: playerBullets) {
            playerBulletsJson.put(String.valueOf(playerBullets.indexOf(bullet)), bullet.toJson(reverse));
        }
        response.put("playerBullets", playerBulletsJson);
        JSONObject enemyBulletsJson = new JSONObject();
        for (Bullet bullet: enemyBullets) {
            enemyBulletsJson.put(String.valueOf(enemyBullets.indexOf(bullet)), bullet.toJson(reverse));
        }
        response.put("enemyBullets", enemyBulletsJson);
        try {
            out.write(response.toString());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.err.println("Impossible to send answer to a client");
        }
    }
}