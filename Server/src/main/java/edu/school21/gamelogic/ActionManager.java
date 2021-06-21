package edu.school21.gamelogic;

import edu.school21.constants.Action;
import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import edu.school21.models.Bullet;
import edu.school21.models.Tank;

import java.awt.*;
import java.util.LinkedList;

public class ActionManager {

    public static void moveBullets(LinkedList<Bullet> bullets, Tank tank) {
        if(!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                if (i == 0) {
                    continue;
                }
                bullets.get(i).move(tank.getSide() == Side.UP);
                if (tank.isGetDamage(bullets.get(i).getArea()) || bullets.get(i).getArea().y < 0 ||
                        bullets.get(i).getArea().y > Constants.arenaHeight) {
                    bullets.remove(i);
                }
            }
        }
    }

    public static int reverseCoordinateY(int coordinateY) {
        return Constants.arenaHeight - coordinateY;
    }

    public static boolean moveTanks(LinkedList<Bullet> bullets, Tank tank, Action action) {
        switch (action) {
            case LEFT: {
                tank.moveLeft();
                System.out.println("Player moves left");
                break;
            }
            case RIGHT: {
                tank.moveRight();
                System.out.println("Player moves right");
                break;
            }
            case SHOOT: {
                bullets.addLast(new Bullet(new Point(tank.getPosition().x + Constants.tankWidth / 2 - Constants.bulletWidth/2,
                        tank.getSide() == Side.UP ? Constants.bulletStartY1 : Constants.bulletStartY2), tank.getSide()));
                System.out.println("Player makes shoot");
                break;
            }
            case NONE: {
                System.out.println("Player does nothing");
                break;
            }
            case OFFLINE: {
                System.out.println("Player is disconnected");
                return false;
            }
        }
        return true;
    }
}
