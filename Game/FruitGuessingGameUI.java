package Game;


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
import javax.swing.SwingWorker;

public class FruitGuessingGameUI extends JFrame implements Serializable{
	private static final long serialVersionUID = 1L;
    private FruitGuessingGame game;
    private JLabel clueLabel;
    private JTextField guessField;
    private JLabel resultLabel;
    private JButton guessButton;
    private JLabel pointsLabel;

    public FruitGuessingGameUI() {
        super("Fruit Guessing Game");
        game = new FruitGuessingGame();

        clueLabel = new JLabel();
        clueLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        guessField = new JTextField(20);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String guess = guessField.getText().trim(); // Create final copy of guess variable
                processGuess(guess);
            }
        });

        pointsLabel = new JLabel("Points: 0");
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(clueLabel, BorderLayout.NORTH);
        mainPanel.add(guessField, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guessButton);

        JPanel pointsPanel = new JPanel();
        pointsPanel.add(pointsLabel);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(pointsPanel, BorderLayout.NORTH);

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

        // Create and execute a SwingWorker to run the game logic in the background
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                game.playGuess(guess);
                return null;
            }

            @Override
            protected void done() {
                try {
                    // Update the UI with the game result
                    resultLabel.setText(game.getGuessResult());

                    if (game.isGameOver()) {
                        clueLabel.setText("Game over!");
                        guessButton.setEnabled(false);
                        game.resetGame(); // Reset the game for a new round
                    } else {
                        clueLabel.setText("Guess the fruit:");
                        guessField.setText("");
                    }

                    updatePointsLabel();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        // Execute the SwingWorker
        worker.execute();
    }

    private void updatePointsLabel() {
        int points = game.getScore();
        pointsLabel.setText("Points: " + points);
    }

    public static void main(String[] args) {
        FruitGuessingGameUI gameUI = new FruitGuessingGameUI();
        gameUI.startGame();
    }
}
