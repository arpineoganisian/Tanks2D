package edu.school21.constants;

import edu.school21.logic.ActionManager;

import java.awt.*;

public final class Constants {
    public static int tankSpeed = 20;
    public static int tankWidth = 81;
    public static int tankHeight = 105;
    public static int bulletWidth = 5;
    public static int bulletHeight = 11;
    public static int bulletSpeed = 40;
    public static int arenaHeight = 800;
    public static int arenaWidth = 800;
    public static int bulletDamage = 5;
    public static int lifeBar = 50;
    public static int bulletStartY1 = lifeBar + tankHeight;
    public static int bulletStartY2 = arenaHeight  - tankHeight - lifeBar - bulletHeight;
    public static Point startPosition1 = new Point(arenaWidth / 2 - tankWidth / 2,
            arenaHeight  - tankHeight - lifeBar);
    public static Point startPosition2 = new Point(arenaWidth / 2 - tankWidth / 2,
            lifeBar);
}
