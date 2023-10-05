package pacman;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyTableCellRenderer extends JLabel implements TableCellRenderer {
    private ImageIcon pacman, scaledPacman;
    private int cellSize;
    private ImageIcon ghost, scaledGhost;
    private ImageIcon wall, scaledWall;
    private ImageIcon dot, bonus, scaledBonus;
    private ImageIcon pacmanU, scaledU, pacmanD,scaledD, pacmanR,scaledR, pacmanL, scaledL;
    public MyTableCellRenderer(int cellSize){
        this.cellSize = cellSize;
        pacman = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/Pacman.png");
        scaledPacman = new ImageIcon(pacman.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        pacmanU = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/PacmanU.png");
        scaledU = new ImageIcon(pacmanU.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        pacmanD = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/PacmanD.png");
        scaledD = new ImageIcon(pacmanD.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        pacmanR = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/PacmanR.png");
        scaledR = new ImageIcon(pacmanR.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        pacmanL = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/PacmanL.png");
        scaledL = new ImageIcon(pacmanL.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        ghost = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/ghost.png");
        scaledGhost = new ImageIcon(ghost.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        wall = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/wall.png");
        scaledWall = new ImageIcon(wall.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        dot = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/dot.png");
        bonus = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/bonus.png");
        scaledBonus = new ImageIcon(bonus.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH));
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value.equals(0)){
            if(Main.pacDirection == 'U')setIcon(scaledU);
            else if(Main.pacDirection == 'D')setIcon(scaledD);
            else if(Main.pacDirection == 'L')setIcon(scaledL);
            else if(Main.pacDirection == 'R')setIcon(scaledR);
            else setIcon(scaledPacman);
        }
        else if(value.equals(2)){
            setIcon(scaledGhost);
        }
        else if(value.equals(-1)){
            setIcon(scaledWall);
        }
        else if(value.equals(1)){
            setIcon(dot);
        }
        else if(value.equals(5)){
            setIcon(scaledBonus);
        }
        else{
            setIcon(null);
        }
        return this;
    }
}
