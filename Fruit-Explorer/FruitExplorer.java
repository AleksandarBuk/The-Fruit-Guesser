import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FruitExplorer {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel clueLabel;
    private JLabel scoreboardLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea messageArea;
    private JButton playButton; // Add a Play Game button

    private FruitDictionary fruitDictionary;
    private String currentFruit;
    private int currentClueIndex;
    private int score;
    private int fruitsGuessed;
    private static final int MAX_GUESSES = 5;

    private Set<String> guessedFruits = new HashSet<>(); // Declare at class level

    public FruitExplorer() {
        frame = new JFrame("Fruit Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel

        JPanel topPanel = new JPanel(); // Create a top panel for labels and clues
        topPanel.setLayout(new GridLayout(6, 2));

        clueLabel = new JLabel("Clue: ");
        topPanel.add(clueLabel);

        scoreboardLabel = new JLabel("Score: " + score);
        topPanel.add(scoreboardLabel);

        // guess input
        guessField = new JTextField();
        guessField.setColumns(20);
        topPanel.add(guessField);

        guessButton = new JButton("Guess");
        topPanel.add(guessButton);

        // Create and configure the Play Game button
        playButton = new JButton("Play Game");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        topPanel.add(playButton); // Add the Play Game button

        mainPanel.add(topPanel, BorderLayout.NORTH);

        messageArea = new JTextArea();
        messageArea.setEditable(false);

        // Create a panel for the message area and guess button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Add the guessButton to the SOUTH position
        bottomPanel.add(guessButton, BorderLayout.SOUTH);

        // Add the messageArea to the CENTER position
        bottomPanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // Add the bottomPanel to the mainPanel
        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        fruitDictionary = new FruitDictionary();
    }

    private void startNewGame() {
        resetFruits(); // Reset guessed fruits
        if (fruitsGuessed >= MAX_GUESSES) {
            messageArea.setText("Game Over! Your Final Score: " + score);
            guessButton.setEnabled(false); // Disable the Guess button
            playButton.setEnabled(true); // Enable play button for play again
        } else {
            initializeGame();
            playButton.setEnabled(false); // Disable the Play Game button after starting the game
        }
    }

    private void initializeGame() {
        currentFruit = getRandomFruit();
        currentClueIndex = 0;
        updateClue();
        score = 0;
        updateScoreboard();
        messageArea.setText("");

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private String getRandomFruit() {
        List<String> availableFruits = new ArrayList<>(fruitDictionary.getFruitNames());

        // Remove already guessed fruits from the availableFruits list
        availableFruits.removeAll(guessedFruits);

        if (availableFruits.isEmpty()) {
            // If all fruits have been guessed, return a message or handle it as needed
            return "All fruits guessed";
        }

        int randomIndex = (int) (Math.random() * availableFruits.size());
        String randomFruit = availableFruits.get(randomIndex);

        // Add the randomly selected fruit to the guessedFruits set
        guessedFruits.add(randomFruit);

        return randomFruit;
    }

    private void updateClue() {
        List<String> clues = fruitDictionary.getCluesForFruit(currentFruit);
        if (currentClueIndex < clues.size()) {
            clueLabel.setText("Clue: " + clues.get(currentClueIndex));
        } else {
            clueLabel.setText("No more clues available.");
        }
    }

    private void resetFruits() {
        guessedFruits.clear();
    }

    private void updateScoreboard() {
        scoreboardLabel.setText("Score: " + score);
    }

    private void checkGuess() {
        String userGuess = guessField.getText();
        if (userGuess.equalsIgnoreCase(currentFruit)) {
            score += 10; // Increase score for correct guess
            fruitsGuessed++; // Increment the number of guessed fruits
            messageArea.setText("Correct! You guessed '" + currentFruit + "'.\n");

            // Check if the maximum number of guesses has been reached
            if (fruitsGuessed >= MAX_GUESSES) {
                messageArea.append("You've completed the first round.\n Do you want to play again?");
                guessButton.setEnabled(false); // Disable the Guess button
                playButton.setEnabled(true); // Enable play button for play again
            } else {
                currentFruit = getRandomFruit();
                currentClueIndex = 0;
                updateClue();
                updateScoreboard();
                guessField.setText("");
            }
        } else {
            messageArea.setText("Incorrect guess. Try again.");
            currentClueIndex++;
            updateClue();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FruitExplorer();
            }
        });
    }
}
