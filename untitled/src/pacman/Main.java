package pacman;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame implements Runnable{
    private List<Integer[]> ghosts= new ArrayList<>();
    public Thread ghostThread, timeThread;
    public BonusThread bonuses;
    private int height;
    private int width;
    private int pacmanx, pacmany;
    Thread thread3;
    private JLabel time;
    private static JLabel heart1 = new JLabel( new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/" +
            "GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/heart.png"));
    private static JLabel heart2 = new JLabel( new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/" +
            "GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/heart.png"));
    private static JLabel heart3 = new JLabel( new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/" +
            "GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/heart.png"));
    private static JLabel heart4 = new JLabel( new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/" +
            "GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/heart.png"));
    public static int multip = 1;
    public static int pacSpeedX = 1;
    public static int ghostsSpeedX = 1;
    static JTable board;
    private int min, sec;
    private JLabel score;
    public Thread menu;
    private static JPanel hearts, info, mainPanel;
    static int lives;
    static int pacScore ;
    static int eatenDots ;
    public static char pacDirection;
    static List<Integer> possibilities = new ArrayList<>();
    static List<Integer> bonusesValue = new ArrayList<>();
    private List<Integer[]> pacVisited = new ArrayList<>();
    private int cellSize;
    public Main(int h, int w){
        min = 0;
        sec = 0;
        height = h;
        width = w;
        cellSize = (int)(Math.min(1200/width,600/height));
        pacDirection = ' ';
        lives = 3;
        pacScore = 0;
        eatenDots = 0;
        board = new JTable(new Board(height,width));
        board.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        board.setRowHeight(cellSize);
        for (int i = 0; i < board.getColumnCount(); i++) {
            TableColumn column = board.getColumnModel().getColumn(i);
            column.setPreferredWidth(cellSize);
            column.setMinWidth(cellSize);
            column.setMaxWidth(cellSize);
        }
        for (int i = 0; i < board.getRowCount(); i++) {
            board.setRowHeight(i, cellSize);
        }
        board.setBackground(new Color(0, 0, 102));
        board.setShowGrid(false);
        board.setDefaultRenderer(Object.class,new MyTableCellRenderer(cellSize));
        info = new JPanel(new GridLayout(1,3));
        info.setBackground(new Color(255, 166, 77));
        mainPanel = new JPanel(new BorderLayout());
        drawHearts();
        score = new JLabel("SCORE: " + pacScore);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setForeground(new Color(230, 0, 0));
        score.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        time = new JLabel(min + ":" + sec);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setForeground(new Color(230, 0, 0));
        time.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        hearts.setBackground(new Color(255, 166, 77));
        info.add(hearts);
        info.add(time);
        info.add(score);
        mainPanel.add(info,BorderLayout.PAGE_END);
        mainPanel.add(board, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjust();
            }
        });
        findPacman();
        board.addKeyListener(new PacmanKeyListener());
        board.requestFocus();
        thread3 = new GenerateBoostThread();
        timeThread =  new Thread(() -> {
            while(lives >0){
                if(sec < 59){
                    sec +=1;

                }
                else {
                    min +=1;
                    sec = 0;
                }
                time.setText(min + ":" + sec);
                //repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timeThread.start();
        ghostThread = new Thread(() -> {
            while(lives > 0){
                if(ghosts.size() > 0){
                    ghosts.clear();
                }
                findGhosts();
                findPacman();
                for(Integer[] ghost: ghosts){
                    moveGhost(ghost[0],ghost[1]);
                }
                try {
                    Thread.sleep((2000*ghostsSpeedX));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ghostThread.start();
        bonuses = new BonusThread();
        bonuses.start();

    }
    private void adjust(){
        int fWidth = getWidth();
        int fHeight = getHeight();
        int newCellSize = Math.min(fWidth / width, fHeight /height);
        board.setRowHeight(newCellSize);
        for (int i = 0; i < board.getColumnCount(); i++) {
            TableColumn column = board.getColumnModel().getColumn(i);
            column.setPreferredWidth(newCellSize);
            column.setMinWidth(newCellSize);
            column.setMaxWidth(newCellSize);
        }
        board.setDefaultRenderer(Object.class,new MyTableCellRenderer(newCellSize));
    }


    private void drawHearts(){
        hearts = new JPanel(new FlowLayout());
            hearts.add(heart1);
            hearts.add(heart2);
            hearts.add(heart3);
    }
    public void findPacman(){
        for(int i = 0; i < board.getRowCount(); i++) {
            for(int j = 0; j < board.getColumnCount(); j++){
                if (board.getValueAt(i,j).equals(0)){
                    pacmanx = i;
                    pacmany = j;
                    break;
                }
            }
        }
        pacVisited.add(new Integer[]{pacmanx, pacmany});
    }
    public synchronized void findGhosts(){
        for(int i = 0; i < board.getRowCount(); i++) {
            for(int j = 0; j < board.getColumnCount(); j++){
                if (board.getValueAt(i,j).equals(2)){
                    ghosts.add(new Integer[]{i,j});
                }
            }
        }
    }

    public synchronized void movePacman(){
        switch(pacDirection){
            case 'U' -> {
                if (pacmanx - 1 >= 0) {
                    Object a = board.getValueAt(pacmanx - 1, pacmany);
                    if (!a.equals(-1)) {
                        if (a.equals(1)) {
                            board.setValueAt(0, pacmanx - 1, pacmany);
                            board.setValueAt(-2, pacmanx, pacmany);
                            pacScore += multip;
                            eatenDots++;
                            score.setText("SCORE: " + pacScore);
                        } else if (a.equals(-2)) {
                            board.setValueAt(0, pacmanx - 1, pacmany);
                            board.setValueAt(-2, pacmanx, pacmany);
                        } else if (a.equals(5)) {
                            board.setValueAt(0, pacmanx - 1, pacmany);
                            board.setValueAt(-2, pacmanx, pacmany);
                            thread3 = new BonusThread();
                            thread3.start();
                        }
                    }
                }
            }
            case 'D' -> {
                if(pacmanx + 1 <= this.height-1) {
                    Object a = board.getValueAt(pacmanx + 1, pacmany);
                    if (!a.equals(-1)) {
                        if (a.equals(1)) {
                            board.setValueAt(0, pacmanx + 1, pacmany);
                            board.setValueAt(-2, pacmanx, pacmany);
                            pacScore += multip;
                            eatenDots++;
                            score.setText("SCORE: " + pacScore);
                        } else if (a.equals(-2)) {
                            board.setValueAt(0, pacmanx + 1, pacmany);
                            board.setValueAt(-2, pacmanx, pacmany);
                        } else if (a.equals(5)) {
                            board.setValueAt(0, pacmanx + 1, pacmany);
                            board.setValueAt(-2, pacmanx, pacmany);
                            thread3 = new BonusThread();
                            thread3.start();
                        }
                    }
                }
            }
            case 'L' -> {
                if(pacmany - 1 >= 0) {
                    Object a = board.getValueAt(pacmanx, pacmany - 1);
                    if (!a.equals(-1)) {
                        if (a.equals(1)) {
                            board.setValueAt(0, pacmanx, pacmany - 1);
                            board.setValueAt(-2, pacmanx, pacmany);
                            pacScore += multip;
                            eatenDots++;
                            score.setText("SCORE: " + pacScore);
                        } else if (a.equals(-2)) {
                            board.setValueAt(0, pacmanx, pacmany - 1);
                            board.setValueAt(-2, pacmanx, pacmany);
                        } else if (a.equals(5)) {
                            board.setValueAt(0, pacmanx, pacmany - 1);
                            board.setValueAt(-2, pacmanx, pacmany);
                            thread3 = new BonusThread();
                            thread3.start();
                        }
                    }
                }
            }
            case 'R' -> {
                if (pacmany + 1 <= this.width-1) {
                    Object a = board.getValueAt(pacmanx, pacmany + 1);
                    if (!a.equals(-1)) {
                        if (a.equals(1)) {
                            board.setValueAt(0, pacmanx, pacmany + 1);
                            board.setValueAt(-2, pacmanx, pacmany);
                            pacScore += multip;
                            eatenDots++;
                            score.setText("SCORE: " + pacScore);
                        } else if (a.equals(-2)) {
                            board.setValueAt(0, pacmanx, pacmany + 1);
                            board.setValueAt(-2, pacmanx, pacmany);
                        } else if (a.equals(5)) {
                            board.setValueAt(0, pacmanx, pacmany + 1);
                            board.setValueAt(-2, pacmanx, pacmany);
                            thread3 = new BonusThread();
                            thread3.start();
                        }
                    }
                }
            }
        }
        repaint();
    }
    public synchronized void moveGhost(int r, int c){
        double minDist = Double.MAX_VALUE;
        int minx = r, miny = c;
        char d = ' ';
        if((r - 1) > 0) {
            if( (board.getValueAt(r-1,c).equals(1) || board.getValueAt(r-1,c).equals(0)
                || board.getValueAt(r-1,c).equals(-2))||board.getValueAt(r-1,c).equals(5)) {
                double dist = Math.sqrt(Math.pow(r - 1 - pacmanx, 2) + Math.pow(c - pacmany, 2));
                if (dist < minDist) {
                minDist = dist;
                d = 'U';
                }
            }
        }
        else d = 'D';
        if((r + 1) <= this.height-1) {
            if(board.getValueAt(r+1,c).equals(1) || board.getValueAt(r+1,c).equals(0)
                    || board.getValueAt(r+1,c).equals(-2)||board.getValueAt(r-1,c).equals(5)) {
                double dist = Math.sqrt(Math.pow(r + 1 - pacmanx, 2) + Math.pow(c - pacmany, 2));
                if (dist < minDist) {
                    minDist = dist;
                    d = 'D';
                }
            }
        }
        else {
            d = 'U';
        }

        if((c - 1) >= 0 ){
                if (board.getValueAt(r,c-1).equals(1) || board.getValueAt(r,c-1).equals(0)
                || board.getValueAt(r,c-1).equals(-2)|| board.getValueAt(r-1,c).equals(5)){
                double dist = Math.sqrt(Math.pow(r - pacmanx, 2) + Math.pow(c - 1 - pacmany, 2));
                if (dist < minDist) {
                    minDist = dist;
                    d = 'L';
                }
            }
        }
        else d = 'R';
        if((c + 1) <= this.width-1){
                if (board.getValueAt(r,c+1).equals(1)|| board.getValueAt(r,c+1).equals(0)
                || board.getValueAt(r,c+1).equals(-2)|| board.getValueAt(r-1,c).equals(5)) {
                    double dist = Math.sqrt(Math.pow(r - pacmanx, 2) + Math.pow(c + 1 - pacmany, 2));
                    if (dist < minDist) {
                        minDist = dist;
                        d = 'R';
                    }
                }
        }
        else d = 'L';
        switch(d){
            case 'U' -> minx-=1;
            case 'D' -> minx+=1;
            case 'L' -> miny-=1;
            case 'R' -> miny+=1;
        }
        if(board.getValueAt(minx,miny).equals(0)){
            lives--;
            if(lives >=0)hearts.remove(0);
            repaint();
            board.setValueAt(-2,pacmanx,pacmany);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            board.setValueAt(0,pacmanx,pacmany);
        }
        else if(r != minx || c != miny){
            if(possibilities.size()>0){
                board.setValueAt(5,r,c);
                board.setValueAt(2,minx,miny);
                bonusesValue.add(possibilities.get(0));
                possibilities.remove(0);
            }
            else {
                boolean isVisited = false;
                for (Integer[] pos : pacVisited) {
                    if (pos[0] == r && pos[1] == c) {
                        board.setValueAt(-2, r, c);
                        board.setValueAt(2, minx, miny);
                        isVisited = true;
                        break;
                    }
                }
                if (!isVisited) {
                    board.setValueAt(1, r, c);
                    board.setValueAt(2, minx, miny);
                }
                board.repaint();
            }
        }
    }
    public static void addHeart(){
        if(hearts.getComponentCount() <= 4) {
            hearts.add(heart4);
            hearts.repaint();
            lives +=1;
        }
    }


    @Override
    public void run() {
        while(lives > 0){
                findPacman();
                movePacman();
                try {
                    Thread.sleep(600 / pacSpeedX);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

