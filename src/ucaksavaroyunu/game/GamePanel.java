package ucaksavaroyunu.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class GamePanel extends Panel implements Runnable {

    private Thread gameThread;
    private final Sprite background = new Background(0, 0, 0);
    private final Tank tank = new Tank(GAME_WIDTH / 2 - 30, GAME_HEIGHT - 100, 0);
    private boolean isRunning;
    private final ArrayList<Bomb> bombs = new ArrayList<>();
    private final ArrayList<Jet> jets = new ArrayList<>();
    private final int jetPositions[];
    private int currentPosition;
    private int life=3, score;

    public GamePanel() {
        this.jetPositions = new int[]{20, 50, 80, 110, 140, 170, 200};
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (gameThread == null) {
            gameThread = new Thread(GamePanel.this);
        }
        gameThread.start();
    }

    @Override
    protected void onDraw(Graphics2D g2D) {
        g2D.setColor(Color.red);
        background.draw(g2D);
        tank.draw(g2D);

        for (Jet jet : jets) {
            jet.draw(g2D);
        }
        for (Bomb bomb : bombs) {
            bomb.draw(g2D);
        }

        g2D.setColor(Color.black);
        
        g2D.drawString("Life: " + life, GAME_WIDTH - 70, 540);

        g2D.drawString("Score: " + score, GAME_WIDTH - 70, 520);
    }

    @Override
    public void run() {
        init();
        while (isRunning) {

            long startTime = System.currentTimeMillis();

            updateGame();
            renderGame();

            long endTime = System.currentTimeMillis() - startTime;
            long waitTime = (MILLISECOND / FPS) - endTime / MILLISECOND;

            try {
                Thread.sleep(waitTime);
            }catch (Exception e) {
            }
        }
    }

    private void init() {
        isRunning = true;
    }

    private void updateGame() {

        if (jets.size() < 5) {
            jets.add(new Jet(GAME_WIDTH, getJetYPosition(), getRandomSpeed()));
            currentPosition++;
        }

        for (int i = 0; i < jets.size(); i++) {
            Jet jet = jets.get(i);
            jet.update();

            for (int j = 0; j < bombs.size(); j++) {
                Bomb bullet = bombs.get(j);
                if (jet.getBound().intersects(bullet.getBound())) {
                    jets.remove(jet);
                    bombs.remove(bullet);
                    score++;
                }
            }

            if (jet.getX() < -80) {
                jets.remove(jet);
                life --;
                if(life <=0) {
                	
        			String message = "GAME OVER...\n" + "Score: " + score;       			
        			JOptionPane.showMessageDialog(this, message);       			
        			System.runFinalization();
        			
        			life = 3;
        			score = 0;
        			jets.clear();
        			bombs.clear();      			
                }
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bullet = bombs.get(i);
            bullet.update();
            if (bullet.getY() < 0) {
            	bombs.remove(bullet);
            }
        }
    }

    private void renderGame() {
        repaint();
    }

    private int getRandomSpeed() {
        return new Random().nextInt(5) + 1;
    }

    private int getJetYPosition() {
        if (currentPosition >= jetPositions.length) {
            currentPosition = 0;
        }
        return jetPositions[currentPosition];
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {
		
		bombs.add(new Bomb(e.getX() - 13, GAME_HEIGHT - 100, 5));		
    }
	@Override
	public void mouseEntered(MouseEvent e) {		
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseDragged(MouseEvent e) {		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
		tank.setX(e.getX() - 30);
	}
}
