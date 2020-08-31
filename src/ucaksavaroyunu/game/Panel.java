package ucaksavaroyunu.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public abstract class Panel extends JPanel implements Helper, MouseListener, MouseMotionListener {
    
    public Panel() {

        this.addMouseListener(Panel.this);
        this.addMouseMotionListener(Panel.this);
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        onDraw(g2D);
    }
    
    protected abstract void onDraw(Graphics2D g2D);
}
