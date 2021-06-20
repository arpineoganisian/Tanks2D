package edu.school21.models;

import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import edu.school21.logic.ActionManager;
import edu.school21.session.game.GameSession;

import org.json.JSONObject;

import java.awt.*;

public class Tank {
    private int hp;
    private Rectangle area;
    private Point position;
    private Side side;

    public Tank(Point position, Side side) {
        this.position = position;
        this.side = side;
        hp = 100;
        area = new Rectangle(position.x, position.y, Constants.tankWidth, Constants.tankHeight);
    }

    public int getHp() {
        return hp;
    }

    public Rectangle getArea() {
        return area;
    }

    public Point getPosition() {
        return position;
    }

    public boolean isGetDamage(Rectangle bullet) {
        if (area.intersects(bullet)) {
            hp -= Constants.bulletDamage;
            return true;
        }
        return false;
    }

    public Side getSide() {
        return side;
    }

    public void moveLeft() {
        if (position.x - Constants.tankSpeed >= 0) {
            position.x -= Constants.tankSpeed;
            area.setRect(position.x, position.y, Constants.tankWidth, Constants.tankHeight);
        }
    }

    public void moveRight() {
        if (position.x <= Constants.arenaWidth - Constants.tankWidth) {
            position.x += Constants.tankSpeed;
            area.setRect(position.x, position.y, Constants.tankWidth, Constants.tankHeight);
        }
    }

    public JSONObject toJson(boolean isPLayer) {
        return new JSONObject().put("hp", hp).put("x", position.x)
                                             .put("y", isPLayer ? Constants.startPosition1.y : Constants.startPosition2.y)
                                             .put("side", side);
    }

    @Override
    public String toString() {
        return "Tank{" +
                "hp=" + hp +
                ", area=" + area +
                ", position=" + position +
                ", side=" + side +
                '}';
    }
}
