package edu.school21.models;

import edu.school21.constants.Side;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import java.awt.*;

public class Bullet {
    private final Point position;
    private final Rectangle bulletArea = new Rectangle();
    private final JSONObject bulletJson = new JSONObject();
    private final Side side;
    private final ApplicationContext context = Context.getContext();
    private final ArenaConfig arenaConfig = context.getBean("arenaConfig", ArenaConfig.class);
    private final int speed = arenaConfig.getBulletSpeed();
    private final int width = arenaConfig.getBulletWidth();
    private final int height = arenaConfig.getBulletHeight();
    private final int damage = arenaConfig.getBulletDamage();

    public Bullet(Side side, Point position) {
        this.side = side;
        this.position = position;
        bulletArea.setRect(this.position.x, this.position.y, width, height);
        System.out.println("Bullet speed:" + speed);
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
