package pacman;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScores implements Serializable {
    private List<Record> scores;
    private static final String file = "/Users/anastasia/Desktop/PJATK/Gui_Proj/" +
            "GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/highScores.txt";

    public HighScores() {
        scores = new ArrayList<>();
    }

    public void addScore(String playerName, int score) {
        Record newScore = new Record(playerName, score);
        scores.add(newScore);
    }

    public List<Record> getScores() {
        return scores;
    }

    public void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file,true))) {
            for (Record score : scores) {
                writer.println(score.getPlayer() + "," + score.getScore());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadScores() {
        scores.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    Record loadedScore = new Record(playerName, score);
                    scores.add(loadedScore);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

