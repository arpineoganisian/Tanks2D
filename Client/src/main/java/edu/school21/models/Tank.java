package edu.school21.models;

import java.awt.*;
import javafx.scene.image.Image;

public class Tank {
    private int hp;
    private Point position;
    private Image image;

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