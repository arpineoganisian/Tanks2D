package edu.school21.models;

import java.awt.*;
import javafx.scene.image.Image;

public class Bullet {
    private Point position;
    private Rectangle area;
    private Image image;

    public Bullet(Point position) {
        this.position = position;
    }

    public void setImage(String string) {
        this.image = new Image(string);
    }

    public Image getImage() {
        return image;
    }

    public Point getPosition() {
        return position;
    }
}
