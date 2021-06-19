package edu.school21.models;

import edu.school21.constants.Side;

import java.awt.*;
import javafx.scene.image.Image;

public class Tank {
    private int hp;
    private Point position;
    private Image image;
    private Side side;
    //TODO может добавть в танк SIDE и смотреть по нему?

    public Tank(Point position) {
        this.position = position;
        hp = 100;
    }

    public void setImage(String string) {
        this.image = new Image(string);
    }

    public Image getImage() {
        return image;
    }

    public int getHp() {
        return hp;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}