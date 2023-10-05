package pacman;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class Board extends AbstractTableModel {
    private int height, width;
    private Object[][] maze;
    static int ghostN = 5;
    public Board(int height, int width){
        this.width = width;
        this.height = height;
        createMaze();
    }


    private void createMaze() {
        this.maze = new Object[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = -1;
            }
        }

        int startX = (int)(Math.random()*(height-2)+1);
        int startY = (int)(Math.random()*(width-2)+1);

        maze[startX][startY] = 1;

        PriorityQueue<Wall> walls = new PriorityQueue<>();
        addWalls(startX, startY, walls);

        while (!walls.isEmpty()) {
            Wall currentWall = walls.poll();
            int x = currentWall.x;
            int y = currentWall.y;

            if (maze[x][y].equals(-1)) {
                int adjacentSpaces = countAdjacentSpaces(x, y);
                if (adjacentSpaces == 1) {
                    maze[x][y] = 1;
                    addWalls(x, y, walls);
                }
            }
        }
        int ph = (int)(height * 0.25);
        int pw = (int)(width * 0.25);
        for(int i = 0; i < ph; i++){
            for(int j = 0; j < pw; j++){
                maze[(int)(Math.random()*(height-2)+1)][(int)(Math.random()*(width-2)+1)] = 1;
            }
        }

        for (int i = 0; i < ghostN; i++) {
            int randomX = (int) (Math.random() * (height - 2) + 1);
            int randomY = (int) (Math.random() * (width - 2) + 1);
            maze[randomX][randomY] = 2;
        }

        int randomX = (int) (Math.random() * (height - 2) + 1);
        int randomY = (int) (Math.random() * (width - 2) + 1);
        maze[randomX][randomY] = 0;
    }


    private class Wall implements Comparable<Wall> {
        int x;
        int y;

        Wall(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Wall other) {
            return 0;
        }
    }
    private int countAdjacentSpaces(int x, int y) {
        int count = 0;
        if (x > 0 && maze[x - 1][y].equals(1))
            count++;
        if (y > 0 && maze[x][y - 1].equals(1))
            count++;
        if (x < height - 1 && maze[x + 1][y].equals(1))
            count++;
        if (y < width - 1 && maze[x][y + 1].equals(1))
            count++;
        return count;
    }
    private void addWalls(int x, int y, PriorityQueue<Wall> walls) {
        if (x > 0 && maze[x -1][y].equals(-1))
            walls.add(new Wall(x - 1, y));
        if (y > 0 && maze[x][y - 1].equals(-1))
            walls.add(new Wall(x, y - 1));
        if (x < height - 1 && maze[x + 1][y].equals(-1))
            walls.add(new Wall(x + 1, y));
        if (y < width - 1 && maze[x][y + 1].equals(-1))
            walls.add(new Wall(x, y + 1));
    }

    @Override
    public int getRowCount() {
        return height;
    }

    @Override
    public int getColumnCount() {
        return width;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return maze[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        maze[rowIndex][columnIndex] = aValue;
    }
}

