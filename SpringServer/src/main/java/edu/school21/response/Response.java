package edu.school21.response;

import edu.school21.constants.Colors;
import edu.school21.models.Bullet;
import edu.school21.models.Tank;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Response {
    private static final JSONObject response = new JSONObject();
    private static final JSONObject playerBulletsJson = new JSONObject();
    private static final JSONObject enemyBulletsJson = new JSONObject();

    public static void sendResponse(BufferedWriter out, Tank player, Tank enemy, List<Bullet> playerBullets,
                                    List<Bullet> enemyBullets, boolean reverse) {

        response.put("player", player.toJson());
        response.put("enemy", enemy.toJson());
        for (int i = 0; i < playerBullets.size(); i++) {
            if (i == 0) {
                playerBulletsJson.put(Integer.toString(i), playerBullets.get(i).toJson(false));
                continue;
            }
            playerBulletsJson.put(Integer.toString(i), playerBullets.get(i).toJson(reverse));
        }

        response.put("playerBullets", playerBulletsJson);
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (i == 0) {
                enemyBulletsJson.put(Integer.toString(i), enemyBullets.get(i).toJson(false));
                continue;
            }
            enemyBulletsJson.put(Integer.toString(i), enemyBullets.get(i).toJson(reverse));
        }
        response.put("enemyBullets", enemyBulletsJson);

        try {
            out.write(response.toString());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.err.println(Colors.ANSI_RED + "Can't send answer to client" + Colors.ANSI_RESET);
        }
        System.out.println(Colors.ANSI_CYAN + "Answer send to client" + Colors.ANSI_RESET);
    }
}
