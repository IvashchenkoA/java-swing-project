package pacman;

public class GenerateBoostThread extends Thread{
    @Override
    public void run() {
        if(Main.bonusesValue.size() > 0){
            int a = Main.bonusesValue.get(0);
            switch(a){
                case 1 -> {
                    Main.multip = 2;
                    Main.bonusesValue.remove(0);
                    try {
                        Thread.sleep(10000);
                        Main.multip = 1;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    Main.pacSpeedX = 2;
                    Main.bonusesValue.remove(0);
                    try {
                        Thread.sleep(10000);
                        Main.pacSpeedX = 1;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 ->{
                    Main.ghostsSpeedX = 2;
                    Main.bonusesValue.remove(0);
                    try {
                        Thread.sleep(10000);
                        Main.ghostsSpeedX = 1;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    Main.addHeart();
                    Main.bonusesValue.remove(0);
                }
                case 5 ->{
                    Main.pacScore += 50;
                    Main.bonusesValue.remove(0);
                }
            }
        }
    }
}
