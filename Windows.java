import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows {
    private int count = 0;
    private Player one, two;
    private Game newMap;
    private int totalRounds;

    

    public void initializeStartWindow() {
        JTextField P1 = new JTextField(20);
        JTextField P2 = new JTextField(20);
        JTextField H = new JTextField(20);
        JTextField L = new JTextField(20);
        JTextField Q = new JTextField(20);
        JTextField N = new JTextField(20);

        JFrame startGame = new JFrame("Start!");
        startGame.setLayout(new GridLayout(7, 2));
        startGame.setSize(400, 300);
        startGame.add(new JLabel("Name of Player1:"));
        startGame.add(P1);
        startGame.add(new JLabel("Name of Player2:"));
        startGame.add(P2);
        startGame.add(new JLabel("Height of map:"));
        startGame.add(H);
        startGame.add(new JLabel("Length of map:"));
        startGame.add(L);
        startGame.add(new JLabel("Quantity of treasure:"));
        startGame.add(Q);
        startGame.add(new JLabel("Total rounds:"));
        startGame.add(N);

        JButton startButton = new JButton("Start Game");
        startGame.add(new JLabel(""));
        startGame.add(startButton);

        startButton.addActionListener(e -> {
            try {
                String name1 = P1.getText();
                String name2 = P2.getText();
                int height = Integer.parseInt(H.getText());
                int length = Integer.parseInt(L.getText());
                int treasure = Integer.parseInt(Q.getText());
                totalRounds = Integer.parseInt(N.getText());

                one = new Player(name1);
                two = new Player(name2);
                newMap = Game.createGame(height, length, treasure);

                startGame.dispose();
                startNextRound();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(startGame, "Invalid input! Please enter valid numbers.");
            }
        });

        startGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame.setVisible(true);
    }

    public void startNextRound() {
        if (count >= totalRounds) {
            showFinalResult();
            return;
        }

        JFrame inputFrame = new JFrame("Round " + (count + 1));
        inputFrame.setLayout(new GridLayout(5, 2));
        inputFrame.setSize(800, 600);

        JTextField row1 = new JTextField();
        JTextField col1 = new JTextField();
        JTextField row2 = new JTextField();
        JTextField col2 = new JTextField();

        inputFrame.add(new JLabel(one.getName() + " - Row:"));
        inputFrame.add(row1);
        inputFrame.add(new JLabel(one.getName() + " - Column:"));
        inputFrame.add(col1);
        inputFrame.add(new JLabel(two.getName() + " - Row:"));
        inputFrame.add(row2);
        inputFrame.add(new JLabel(two.getName() + " - Column:"));
        inputFrame.add(col2);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int r1 = Integer.parseInt(row1.getText());
                    int c1 = Integer.parseInt(col1.getText());
                    newMap.P(one, r1, c1);

                    int r2 = Integer.parseInt(row2.getText());
                    int c2 = Integer.parseInt(col2.getText());
                    newMap.P(two, r2, c2);

                    inputFrame.dispose();
                    showRoundResult();
                    count++;
                    if (count < totalRounds) {
                        startNextRound();
                    } else {
                         newMap.getMap();
                         showFinalResult();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(inputFrame, "Invalid coordinates!");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(inputFrame, "Coordinates out of map range!");
                }
            }
        });

        inputFrame.add(submitButton);
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputFrame.setVisible(true);
    }

    public void showRoundResult() {
        JFrame resultFrame = new JFrame("Round Result");
        resultFrame.setLayout(new GridLayout(2, 1));
        resultFrame.setSize(300, 200);

        JLabel p1Score = new JLabel(one.getName() + " Score: " + one.getScore());
        JLabel p2Score = new JLabel(two.getName() + " Score: " + two.getScore());

        resultFrame.add(p1Score);
        resultFrame.add(p2Score);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> resultFrame.dispose());
        resultFrame.add(closeButton);

        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setVisible(true);
    }

    public void showFinalResult() {
        JOptionPane.showMessageDialog(null, newMap.whoWin(one, two), "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}