package ucaksavaroyunu.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Jet extends Sprite {

    private final Image image;

    public Jet(int x, int y, int speed) {
        super(x, y, speed);
        this.image = new ImageIcon(getClass().getResource("/images/jet.png")).getImage();
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }

    public void update() {
        setX(getX() - getSpeed());
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), this.image.getWidth(null), this.image.getHeight(null));
    }
}
