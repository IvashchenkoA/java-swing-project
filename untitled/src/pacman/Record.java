package pacman;

import java.io.Serializable;

public class Record implements Serializable {
    private String player;
    private int score;

    public Record(String player, int score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return player + "'s score: " + score;
    }
}

