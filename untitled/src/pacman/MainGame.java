package pacman;

import javax.swing.*;

public class MainGame extends Thread{

    private boolean levelUp;
     static boolean newMenu;
     public Thread m1;
    public Main m2;
    private int height;
    private int width;
    private int dots;
    public MainGame(int h, int w){
        height = h;
        width = w;
        levelUp = false;
        newMenu = false;
        m2 = new Main(height,width);
        m1 = new Thread(m2);
        m1.start();
    }
    private void countDots(){
        dots = 0;
        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < width; j++){
                if(Main.board.getValueAt(i,j).equals(1)){
                    dots++;
                }
            }
        }
    }
    public void run(){
        while(Main.lives > 0){
            countDots();
            if( dots == 0){
                String name = JOptionPane.showInputDialog(null,
                        "You won!. Enter your name to save a score: " + Main.pacScore,"Name Input", JOptionPane.PLAIN_MESSAGE);
                Menu.highScores.addScore(name, Main.pacScore);
                Menu.highScores.saveScores();
                newMenu = true;
            }
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }
        if(Main.lives == 0){
            String name = JOptionPane.showInputDialog(null,
                    "Game over. Enter your name to save a score: " + Main.pacScore,"Name Input", JOptionPane.PLAIN_MESSAGE);
            Menu.highScores.addScore(name, Main.pacScore);
            Menu.highScores.saveScores();
            newMenu = true;
        }
    }
}

