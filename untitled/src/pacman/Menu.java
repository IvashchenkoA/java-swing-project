package pacman;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;


import static javax.swing.SwingConstants.CENTER;

public class Menu extends JFrame {
     Thread m;
     MainGame main;
    public static HighScores highScores;
    private JList<Record> highScoresList;
    private DefaultListModel<Record> listModel;
    public Menu(){
        highScores = new HighScores();
        listModel = new DefaultListModel<>();
        highScoresList = new JList<>(listModel);
        ImageIcon logo = new ImageIcon("/Users/anastasia/Desktop/PJATK/Gui_Proj/" +
                "GUI_FTS_EN_2223S_PRO2_S27247_INTELLIJIDEA/pacmanPro/untitled/logoCut.png");
        Image image = logo.getImage();
        Image newimg = image.getScaledInstance(450, 220,  java.awt.Image.SCALE_SMOOTH);
        logo = new ImageIcon(newimg);
        JLabel label = new JLabel(logo);
        label.setHorizontalAlignment(CENTER);
        label.setForeground(Color.WHITE);
        JButton button1 = new JButton("NEW GAME");
        button1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        button1.setOpaque(true);
        button1.setBorder(new LineBorder(new Color(0, 0, 102)));
        button1.setBackground(new Color(255, 166, 77));
        button1.setForeground(new Color(230, 0, 0));
        button1.addActionListener(e -> {
            dispose();
            int height_;
            int width_;
            while(true){
                JPanel mainP = new JPanel(new GridLayout(3,2));
                JLabel label3 = new JLabel("Enter board ");
                label3.setHorizontalTextPosition(JLabel.RIGHT);
                label3.setHorizontalAlignment(JLabel.RIGHT);
                label3.setForeground(new Color(255, 166, 77));
                label3.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
                mainP.add(label3);
                JLabel label4 = new JLabel("size: ");
                label4.setForeground(new Color(255, 166, 77));
                label4.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
                mainP.add(label4);
                JTextField height = new JTextField(10);
                height.setBackground(new Color(255, 166, 77));
                height.setForeground(new Color(160, 0, 0));
                height.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
                JTextField width = new JTextField(10);
                width.setBackground(new Color(255, 166, 77));
                width.setForeground(new Color(160, 0, 0));
                width.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
                JLabel label1 = new JLabel("height (10-100):");
                label1.setForeground(new Color(255, 166, 77));
                label1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                mainP.add(label1);
                mainP.add(height);
                JLabel label2 = new JLabel("width (10-100):");
                label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                label2.setForeground(new Color(255, 166, 77));
                mainP.add(label2);
                mainP.add(width);
                mainP.setBackground(new Color(0, 0, 102));
                mainP.setForeground(new Color(255, 166, 77));
                mainP.setPreferredSize(new Dimension(400,180));
                int result = JOptionPane.showConfirmDialog(null, mainP, "",  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
                if (result == JOptionPane.OK_OPTION) {
                    int heightRatio = Integer.parseInt(height.getText());
                    int widthRatio = Integer.parseInt(width.getText());

                    if (heightRatio < 10 || heightRatio > 100 || widthRatio < 10 || widthRatio > 100) {
                        JOptionPane.showMessageDialog(null, "Invalid values! Please enter values between 10 and 100.");
                        continue;
                    }

                    height_ = heightRatio;
                    width_ = widthRatio;
                    break;
                }
                else {
                    System.exit(0);
                }
            }
            main = new MainGame(height_,width_);
            m = main;
            m.start();
        });
        JButton button2 = new JButton("HIGH SCORES");
        button2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        button2.setOpaque(true);
        button2.setBorder(new LineBorder(new Color(0, 0, 102)));
        button2.setBackground(new Color(255, 166, 77));
        button2.setForeground(new Color(230, 0, 0));
        button2.addActionListener(e -> {
            highScores.loadScores();
            displayHighScores();
        });
        JButton button3 = new JButton("EXIT");
        button3.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        button3.setOpaque(true);
        button3.setBorder(new LineBorder(new Color(0, 0, 102)));
        button3.setBackground(new Color(255, 166, 77));
        button3.setForeground(new Color(230, 0, 0));
        button3.addActionListener(e -> System.exit(0));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        add(panel);
        setTitle("Pacman");
        panel.setBackground(new Color(0, 0, 102));
        setVisible(true);
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void displayHighScores() {
        listModel.clear();
        List<Record> scores = highScores.getScores();
        scores.forEach(listModel::addElement);

        JScrollPane scrollPane = new JScrollPane(highScoresList);
        scrollPane.setPreferredSize(new Dimension(350, 450));
        highScoresList.setBackground(new Color(50, 0, 130));
        highScoresList.setForeground(Color.WHITE);
        highScoresList.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        JFrame frame = new JFrame("High Scores");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
