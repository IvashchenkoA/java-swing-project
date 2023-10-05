package pacman;

import javax.swing.*;

public class MenuThread extends Thread{
    Menu menu;
    public MenuThread(){
        SwingUtilities.invokeLater(() -> menu = new Menu());
    }
    @Override
    public void run() {
        while(!Thread.interrupted()){
            if(MainGame.newMenu) {
                MainGame.newMenu = false;
                menu.main.m2.ghostThread.interrupt();
                menu.main.m1.interrupt();
                menu.main.interrupt();
                menu.main.m2.dispose();
                menu.main.m2.thread3.interrupt();
                menu.main.m2.bonuses.interrupt();
                SwingUtilities.invokeLater(() -> menu = new Menu());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
}


