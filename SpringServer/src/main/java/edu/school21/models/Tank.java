package edu.school21.models;

import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Scope("prototype")
public class Tank {
    @Value("${game.tankSpeed}")
    private int speed;

    @Value("${game.tankHp}")
    private int hp;

    @Value("${game.tankWidth}")
    private int width;

    @Value("${game.tankHeight}")
    private int height;

    private final ArenaConfig arenaConfig = Context.getContext().getBean("arenaConfig", ArenaConfig.class);
    private Side side;
    private final Rectangle tankArea = new Rectangle();
    private final Point position = new Point();
    private final JSONObject tankJson = new JSONObject();

    public Tank() {
        position.x = arenaConfig.getTankStartPositionX();
    }

    public void moveRight() {
        if (position.x + width <= arenaConfig.getArenaWidth()) {
            position.x += speed;
            tankArea.setRect(position.x, position.y, width, height);
        }
    }

    public void setSide(Side side) {
        this.side = side;
        if (this.side == Side.UP) {
            position.y = arenaConfig.getTankStartPositionUp();
        }
        else {
            position.y = arenaConfig.getTankStartPositionDown();
        }
        tankArea.setRect(position.x, position.y, width, height);
    }

    public Side getSide() {
        return side;
    }

    public Rectangle getTankArea() {
        return tankArea;
    }

    public int getHp() {
        return hp;
    }

    public void moveLeft() {
        if (position.x - speed >= 0) {
            position.x -= speed;
            tankArea.setRect(position.x, position.y, width, height);
        }
    }

    public boolean hasGetDamage(Rectangle bullet) {
        if (tankArea.intersects(bullet)) {
            hp -= arenaConfig.getBulletDamage();
            return true;
        }
        return false;
    }

    public JSONObject toJson() {
        return tankJson.put("x", position.x).put("y", position.y).put("hp", hp);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
