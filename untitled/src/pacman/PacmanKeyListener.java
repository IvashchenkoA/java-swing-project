package pacman;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacmanKeyListener extends KeyAdapter {
    private boolean ctrl = false , shift = false;
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Main.pacDirection = 'U';
        } else if (keyCode == KeyEvent.VK_DOWN) {
            Main.pacDirection = 'D';
        } else if (keyCode == KeyEvent.VK_LEFT) {
            Main.pacDirection = 'L';
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            Main.pacDirection = 'R';
        }
        else if(keyCode == KeyEvent.VK_CONTROL){
            ctrl = true;
        }
        else if(keyCode == KeyEvent.VK_SHIFT){
            shift = true;
        }
        else if(ctrl && shift && keyCode == KeyEvent.VK_Q){
            String name = JOptionPane.showInputDialog(null,
                    "Enter your name to save a score: " + Main.pacScore,"Name Input", JOptionPane.PLAIN_MESSAGE);
            Menu.highScores.addScore(name, Main.pacScore);
            Menu.highScores.saveScores();
            MainGame.newMenu = true;
        }
    }
}
