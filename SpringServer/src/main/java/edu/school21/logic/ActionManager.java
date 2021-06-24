package edu.school21.logic;

import edu.school21.action.Action;
import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import edu.school21.models.ArenaConfig;
import edu.school21.models.Bullet;
import edu.school21.models.Context;
import edu.school21.models.Tank;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.util.List;

public class ActionManager {

    private static final ApplicationContext context = Context.getContext();
    private static final ArenaConfig arenaConfig = context.getBean("arenaConfig", ArenaConfig.class);

    public static void moveBullets(List<Bullet> bullets, Tank tank) {
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                if (i == 0) {
                    continue;
                }
                bullets.get(i).move();
                if (tank.hasGetDamage(bullets.get(i).getBulletArea()) || bullets.get(i).getBulletArea().y < 0 ||
                    bullets.get(i).getBulletArea().y > arenaConfig.getArenaHeight()) {
                    bullets.remove(i);
                }
            }
        }
    }

    public static void moveTanks(List<Bullet> bullets, Tank tank, Action action) {
        switch (action) {
            case LEFT -> tank.moveLeft();
            case RIGHT -> tank.moveRight();
            case SHOOT -> bullets.add(new Bullet(tank.getSide(), new Point(tank.getTankArea().x + tank.getWidth() / 2 -
                    arenaConfig.getBulletWidth(), tank.getSide() == Side.UP ? arenaConfig.getBulletStartPositionUp() :
                    arenaConfig.getBulletStartPositionDown())));
            case NONE -> System.out.println("Player does nothing");
            case OFFLINE -> System.out.println("Player disconnected");
        }
    }
}
