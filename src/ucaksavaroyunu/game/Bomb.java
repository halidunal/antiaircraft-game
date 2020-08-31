package ucaksavaroyunu.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bomb extends Sprite {

    public Bomb(int x, int y, int speed) {
        super(x, y, speed);
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.setColor(Color.BLACK);
        g2D.fillOval(getX() + 8, getY() - 5, 10, 10);
    }

    public void update() {
        setY(getY() - getSpeed());
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), 10, 10);
    }
}
