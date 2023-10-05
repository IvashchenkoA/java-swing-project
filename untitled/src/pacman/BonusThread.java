package pacman;

public class BonusThread extends Thread{
    @Override
    public void run() {
        while(!Thread.interrupted()){
            double p;
            p = Math.random();
            if(p <= 0.25){
                Main.possibilities.add((int) ((Math.random() * 5) + 1));
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
