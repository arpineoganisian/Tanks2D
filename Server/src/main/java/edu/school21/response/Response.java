package edu.school21.response;

import edu.school21.constants.Colors;
import edu.school21.models.Bullet;
import edu.school21.models.Tank;

import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Response {

    public static void sendResponse(BufferedWriter out, Tank player, Tank enemy, LinkedList<Bullet> playerBullets,
                                    LinkedList<Bullet> enemyBullets, boolean reverse) {

        JSONObject response = new JSONObject();
        JSONObject playerBulletsJson = new JSONObject();
        JSONObject enemyBulletsJson = new JSONObject();
        response.put("player", player.toJson(true));
        response.put("enemy", enemy.toJson(false));
        loop(playerBullets, reverse, playerBulletsJson);
        response.put("playerBullets", playerBulletsJson);
        loop(enemyBullets, reverse, enemyBulletsJson);
        response.put("enemyBullets", enemyBulletsJson);

        try {
            out.write(response.toString());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.err.println(Colors.ANSI_RED + "Impossible to send answer to a client" + Colors.ANSI_RESET);
        }
        System.out.println(Colors.ANSI_CYAN + "Answer was sent to a client" + Colors.ANSI_RESET);
    }

    private static void loop(LinkedList<Bullet> bullets, boolean reverse, JSONObject bulletsJson) {
        for (int i = 0; i < bullets.size(); i++) {
            bulletsJson.put(Integer.toString(i), bullets.get(i).toJson(reverse));
        }
    }
}
