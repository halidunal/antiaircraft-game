/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucaksavaroyunu;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ucaksavaroyunu.game.GamePanel;

public class Main {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame gameFrame = new JFrame("Uçaksavar Oyunu");
                gameFrame.add(new GamePanel());
                gameFrame.setResizable(false);
                gameFrame.pack();
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
            }
        });
    }
}
