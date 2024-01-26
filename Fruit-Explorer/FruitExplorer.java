import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

        guessField = new JTextField();
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
        bottomPanel.add(new JScrollPane(messageArea), BorderLayout.LINE_END);

        // Add the bottomPanel to the mainPanel
        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        fruitDictionary = new FruitDictionary();
    }

    private void startNewGame() {
        initializeGame();
        playButton.setEnabled(false); // Disable the Play Game button after starting the game
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
        List<String> fruitNames = new ArrayList<>(fruitDictionary.getFruitNames());
        int randomIndex = (int) (Math.random() * fruitNames.size());
        return fruitNames.get(randomIndex);
    }

    private void updateClue() {
        List<String> clues = fruitDictionary.getCluesForFruit(currentFruit);
        if (currentClueIndex < clues.size()) {
            clueLabel.setText("Clue: " + clues.get(currentClueIndex));
        } else {
            clueLabel.setText("No more clues available.");
        }
    }

    private void updateScoreboard() {
        scoreboardLabel.setText("Score: " + score);
    }

    private void checkGuess() {
        String userGuess = guessField.getText();
        if (userGuess.equalsIgnoreCase(currentFruit)) {
            score += 10; // Increase score for correct guess
            messageArea.setText("Correct! You guessed '" + currentFruit + "'.");
            currentFruit = getRandomFruit();
            currentClueIndex = 0;
            updateClue();
            updateScoreboard();
            guessField.setText("");
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

// add the fruit guessing limit 5 fruits max
// make sure to organize elements better
// make sure to add 20 fruits and improve their clues in educational purposes 
// organize clues from hardest to easiest