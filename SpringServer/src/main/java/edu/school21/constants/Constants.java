package edu.school21.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    @Value("${game.arenaHeight:1}")
    private int arenaHeight;
    @Value("${game.arenaWidth:1}")
    private int arenaWidth;
    @Value("${game.lifeBar:1}")
    private int lifeBarSize;
    @Value("${game.bulletStartPositionUp:1}")
    private int bulletStartPositionUp;
    @Value("${game.bulletStartPositionDown:1}")
    private int bulletStartPositionDown;
    @Value("${game.tankStartPositionX:1}")
    private int tankStartPositionX;
    @Value("${game.tankStartPositionUp:1}")
    private int tankStartPositionUp;
    @Value("${game.tankStartPositionDown:1}")
    private int tankStartPositionDown;

    public int getArenaHeight() {
        return arenaHeight;
    }

    public int getArenaWidth() {
        return arenaWidth;
    }

    public int getLifeBarSize() {
        return lifeBarSize;
    }

    public int getBulletStartPositionUp() {
        return bulletStartPositionUp;
    }

    public int getBulletStartPositionDown() {
        return bulletStartPositionDown;
    }

    public int getTankStartPositionX() {
        return tankStartPositionX;
    }

    public int getTankStartPositionUp() {
        return tankStartPositionUp;
    }

    public int getTankStartPositionDown() {
        return tankStartPositionDown;
    }
}
