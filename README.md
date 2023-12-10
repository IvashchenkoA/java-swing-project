# java swing game project

My project is an implementation of a simple game inspired by Pacman written in Java Swing.

## Description

The application allows a user to play a Pacman-like game, which keeps track of the players' history and the scored points.

## Installation

If you are new to Java, you may need to install jdk - follow [this](https://www3.ntu.edu.sg/home/ehchua/programming/howto/jdk_howto.html) link for further instructions.

It is up to you to choose the IDE, but the project was written with the help of IntelliJ IDEA - [install](https://www.jetbrains.com/idea/download/?source=google&medium=cpc&campaign=EMEA_en_PL_IDEA_Search&term=install+java+jdk&content=602281582542&gad=1&gclid=EAIaIQobChMI0oLO7OiEgwMV159oCR1abQ2cEAAYASABEgL9efD_BwE&section=mac)

To run and test the application, you should execute the Presentation.java 


## Implementation details

here is a brief overview of my application's functionalities.

```java

// a feature used to create a model board for the game
public class Board extends AbstractTableModel

private void createMaze() --> this method creates a unique maze for a pacman using the DFS algorithm.

// the main Thread which basically stands for all the UI, keeps the track of time, holds the number of lives user posseses, moves the main elements on the board and adjusts the images size.

public class Main extends JFrame implements Runnable

// the thread which allows user to play multiple levels
public class MainGame extends Thread

// a JFrame which is implemented to have a navigation menu to start the game or see the saved scores of yyour previous game attempts
public class Menu extends JFrame

// a Thread created for menu, which allows us to make the game endless unless the user wishes to exit
public class MenuThread extends Thread

//a class used to keep track of the history of user's game attempts
public class HighScores implements Serializable

// a class which represents an entity to be written in the HighScores menu
public class Record implements Serializable

// additional threads which implement the functionalities of having bonuses and randomly generated boosts
public class BonusThread extends Thread
public class GenerateBoostThread extends Thread

//a class for painting a board and uploading all the neccesarry images and icons, basically the corrrespondence of the code and the GUI
public class MyTableCellRenderer extends JLabel implements TableCellRenderer

//a class which makes pacman navigation available
public class PacmanKeyListener extends KeyAdapter

```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)# java-swing-project
