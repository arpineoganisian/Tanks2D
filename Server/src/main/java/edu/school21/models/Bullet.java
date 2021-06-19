package edu.school21.models;

import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import edu.school21.logic.ActionManager;
import org.json.JSONObject;

import java.awt.*;

public class Bullet {
    private Side side;
    private Point position;
    private Rectangle area;

    public Bullet(Point position, Side side) {
        this.position = position;
        this.side = side;
        area = new Rectangle(position.x, position.y, Constants.bulletWidth, Constants.bulletHeight);
    }

    public Point getPosition() {
        return position;
    }

    public Rectangle getArea() {
        return area;
    }

    public void move(boolean isUp) {
        if (isUp) {
            position.y -= Constants.bulletSpeed;
        }
        else {
            position.y += Constants.bulletSpeed;
        }
        area.setRect(position.x, position.y, Constants.bulletWidth, Constants.bulletHeight);
    }

    public Side getSide() {
        return side;
    }

    public JSONObject toJson(boolean reverse) {
        return new JSONObject().put("x", position.x)
                               .put("y", reverse ? ActionManager.reverseCoordinateY(position.y) : position.y)
                               .put("side", side);
    }
}
