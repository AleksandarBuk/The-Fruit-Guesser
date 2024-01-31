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
    private JTextArea ScoreField;
    private JButton guessButton;
    private JTextArea messageArea;
    private JButton playButton; // Add a Play Game button

    private FruitDictionary fruitDictionary;
    private String currentFruit;
    private int currentClueIndex;
    private int score;
    private int fruitsGuessed;
    private static final int MAX_ = 5;

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
        
        ScoreField = new JTextArea();
        ScoreField.setColumns(20);
        ScoreField.setVisible(false); // Initially, the ScoreField is invisible
        ScoreField.setEditable(false); // Make the ScoreField not editable
        topPanel.add(ScoreField);


        // Create and configure the Play Game button
        playButton = new JButton("Play Game");
        playButton.setPreferredSize(new Dimension(100, 40)); // Set preferred size
        
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
                currentClueIndex = 0;
                guessField.setVisible(true); // Show the input field
                messageArea.setVisible(true);
                ScoreField.setVisible(false); // Hide the ScoreField
            }
        });

        mainPanel.add(topPanel, BorderLayout.NORTH);

        messageArea = new JTextArea();
        messageArea.setColumns(30);
        messageArea.setEditable(false);

        // Create a panel for the message area and guess button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); // Use BorderLayout

        // Create a JScrollPane for the messageArea
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Set alignment for messageArea
        messageArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Horizontal alignment
        messageArea.setAlignmentY(Component.TOP_ALIGNMENT);    // Vertical alignment (top)

        // Reduce the preferred height of the JScrollPane by half
        Dimension scrollPaneSize = scrollPane.getPreferredSize();
        scrollPaneSize.height = 100; // Set the height to 100 pixels
        scrollPane.setPreferredSize(scrollPaneSize);

        // Add the JScrollPane as the center component
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the guess button
        JPanel guessButtonPanel = new JPanel();
        guessButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create the "Guess" button and set its preferred size
        guessButton = new JButton("Guess");
        guessButton.setPreferredSize(new Dimension(100, 40)); // Set preferred size
        guessButtonPanel.add(guessButton);
        guessButtonPanel.add(playButton); // Add the Play Game button

        // Add the guessButtonPanel to the bottomPanel's SOUTH position
        bottomPanel.add(guessButtonPanel, BorderLayout.SOUTH);

        // Add the bottomPanel to the mainPanel
        mainPanel.add(bottomPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);

        // Initialize the Fruit Dictionary
        fruitDictionary = new FruitDictionary();

        // Disable guessButton initially
        guessButton.setEnabled(false);
    }

    private void startNewGame() {
        resetFruits(); // Reset guessed fruits
        if (fruitsGuessed >= MAX_) {
            messageArea.setText("Game Over! Your Final Score: " + score);
            guessButton.setEnabled(false); // Disable the Guess button
            playButton.setEnabled(true); // Enable play button for play again
            ScoreField.setVisible(true); // Show the ScoreField
            ScoreField.setText("Final Score: " + score); // Display final score in ScoreField
            currentClueIndex = 0;
            
        } else {
            initializeGame();
            guessButton.setEnabled(true); // Enable the Guess button after starting the game
            playButton.setEnabled(false); // Disable the Play Game button after starting the game
            messageArea.setText(""); // Clear the messageArea
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
        fruitsGuessed = 0;
    }

    private void updateScoreboard() {
        scoreboardLabel.setText("Score: " + score);
    }

    private void checkGuess() {
        String userGuess = guessField.getText();
        if (userGuess.equalsIgnoreCase(currentFruit)) {
            int points = 10 - currentClueIndex; // Calculate points based on number of clues taken
            System.out.print("These are current points" + points);
            score += points; // Increase score based on points calculated
            fruitsGuessed++; // Increment the number of guessed fruits
            messageArea.setText("Correct! You guessed '" + currentFruit + "'.\n");

            if (fruitsGuessed >= MAX_) {
                guessButton.setEnabled(false); // Disable the Guess button
                playButton.setEnabled(true); // Enable play button for play again
                guessField.setVisible(false); // Hide the input field
                messageArea.setVisible(false);
                ScoreField.setVisible(true);
                updateScoreboard();

                ScoreField.setText("Final Score: " + score); // Display final score in ScoreField
         
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
        guessField.setText(""); // Clear the text in the input field
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FruitExplorer();
            }
        });
    }
}
