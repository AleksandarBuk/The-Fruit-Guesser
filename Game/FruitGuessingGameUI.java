package Game;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class FruitGuessingGameUI extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private FruitGuessingGame game;
    private JLabel clueLabel;
    private JTextField guessField;
    private JLabel resultLabel;
    private JButton guessButton;
    private JLabel pointsLabel;
    private JButton playAgainButton;
    private JLabel statisticsLabel;
    private GameStatistics gameStatistics;

    public FruitGuessingGameUI() {
        super("Fruit Guessing Game");
        game = new FruitGuessingGame();
        gameStatistics = new GameStatistics();

        clueLabel = new JLabel();
        clueLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        guessField = new JTextField(20);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        
        statisticsLabel = new JLabel();
        statisticsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String guess = guessField.getText().trim();
                processGuess(guess);
                guessField.setText(""); // Clear the guessField after pressing the guessButton
            }
        });

        playAgainButton = new JButton("Play Again");
        playAgainButton.setEnabled(false);
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        
        Dimension windowSize = new Dimension(400,400);
        setPreferredSize(windowSize);
        
        setResizable(false); // Set the Resizable property to false

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.add(statisticsLabel);

//        setLayout(new BorderLayout());
//        add(mainPanel, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
//        add(pointsPanel, BorderLayout.NORTH);
//        add(statisticsPanel, BorderLayout.WEST); // Add the statistics panel to the left side

        
        pointsLabel = new JLabel("Points: 0");
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(clueLabel, BorderLayout.NORTH);
        mainPanel.add(guessField, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guessButton);
        buttonPanel.add(playAgainButton);

        JPanel pointsPanel = new JPanel();
        pointsPanel.add(pointsLabel);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(pointsPanel, BorderLayout.NORTH);
        add(statisticsPanel, BorderLayout.WEST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void startGame() {
        clueLabel.setText("Welcome to the Fruit Guessing Game!");
        setVisible(true);
    }

    public void processGuess(String guess) {
        if (guess.isEmpty()) {
            resultLabel.setText("Please enter a guess.");
            return;
        }

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                game.playGuess(guess);
                return null;
            }

            @Override
            protected void done() {
                try {
                    resultLabel.setText(game.getGuessResult());

                    if (game.isGameOver()) {
                        if (game.getRemainingGuesses() <= 0) {
                            clueLabel.setText("Game over!");
                            guessButton.setEnabled(false);
                            playAgainButton.setEnabled(true);
                        } else {
                            clueLabel.setText("Incorrect guess. Remaining guesses: " + game.getRemainingGuesses());
                        }
                    } else {
                        clueLabel.setText("Guess the fruit:");
                    }

                    updatePointsLabel();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        worker.execute();
    }
    
    private void updateStatisticsLabel() {
        int gamesPlayed = gameStatistics.getTotalGames();
        int gamesWon = gameStatistics.getTotalWins();
        int gamesLost = gameStatistics.getTotalLosses();
        String statisticsText = "Games played: " + gamesPlayed + " | Games won: " + gamesWon + " | Games lost: " + gamesLost;
        statisticsLabel.setText(statisticsText);
    }

    private void updatePointsLabel() {
        int points = game.getScore();
        pointsLabel.setText("Points: " + points);
    }

    private void startNewGame() {
        game.resetGame();
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        remove(playAgainButton);
        add(guessButton, BorderLayout.SOUTH);
        resultLabel.setText("");
        clueLabel.setText("Guess the fruit:");
        guessField.setText("");
        updatePointsLabel();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FruitGuessingGameUI gameUI = new FruitGuessingGameUI();
                gameUI.startGame();
            }
        });
    }
}
