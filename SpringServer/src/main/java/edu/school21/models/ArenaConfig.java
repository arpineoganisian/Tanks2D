package edu.school21.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ArenaConfig {
    @Value("${game.arenaHeight:1}")
    private int arenaHeight;
    @Value("${game.arenaWidth:1}")
    private int arenaWidth;
    @Value("${game.lifeBarSize}")
    private int lifeBarSize;
    @Value("${game.bulletStartPositionUp}")
    private int bulletStartPositionUp;
    @Value("${game.bulletStartPositionDown}")
    private int bulletStartPositionDown;
    @Value("${game.tankStartPositionX:1}")
    private int tankStartPositionX;
    @Value("${game.tankStartPositionUp:1}")
    private int tankStartPositionUp;
    @Value("${game.tankStartPositionDown:1}")
    private int tankStartPositionDown;
    @Value("${game.bulletSpeed}")
    private int bulletSpeed;
    @Value("${game.bulletWidth}")
    private int bulletWidth;
    @Value("${game.bulletHeight}")
    private int bulletHeight;
    @Value("${game.bulletDamage}")
    private int bulletDamage;

    public ArenaConfig() {}

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

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public int getBulletWidth() {
        return bulletWidth;
    }

    public int getBulletHeight() {
        return bulletHeight;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

}
