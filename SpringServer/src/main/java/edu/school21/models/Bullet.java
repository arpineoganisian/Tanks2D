package edu.school21.models;

import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;

public class Bullet {
    @Value("${game.bulletSpeed}")
    private int speed;
    @Value("${game.bulletWidth}")
    private int width;
    @Value("${game.bulletHeight}")
    private int height;
    @Value("${game.bulletDamage}")
    private int damage;
    private Point position = new Point();
    private final Rectangle bulletArea = new Rectangle();
    private final JSONObject bulletJson = new JSONObject();
    private final Side side;
    private final ApplicationContext context = Context.getContext();
    private final ArenaConfig arenaConfig = context.getBean("arenaConfig", ArenaConfig.class);

    public Bullet(Side side, Point position) {
        this.position = position;
        bulletArea.setRect(this.position.x, this.position.y, width, height);
        this.side = side;
    }

    public void move() {
        if (side == Side.UP) {
            position.y += speed;
        }
        else {
            position.y -= speed;
        }
        bulletArea.setRect(position.x, position.y, width, height);
    }

    public JSONObject toJson(boolean reverse) {
        return bulletJson.put("x", position.x)
                .put("y", reverse ? arenaConfig.getArenaHeight() - position.y : position.y)
                .put("side", side);
    }

    public int getDamage() {
        return damage;
    }

    public Rectangle getBulletArea() {
        return bulletArea;
    }
}
