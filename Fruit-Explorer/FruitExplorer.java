import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FruitExplorer {
    private JFrame frame;
    private JLabel clueLabel, scoreboardLabel;
    private JPanel mainPanel;
    private JTextField guessField;
    private JTextArea scoreField, messageArea;
    private JButton guessButton, playButton;

    private FruitDictionary fruitDictionary;
    private String currentFruit;
    private int currentClueIndex, score, fruitsGuessed;
    private Set<String> guessedFruits = new HashSet<>();
    private static final int MAX_GUESSES = 5;
    private int currentGuessCount;

    
    public FruitExplorer() {
        initializeUI();
        fruitDictionary = new FruitDictionary();
    }

    private void initializeUI() { // inicijalizacija interfejsa
        frame = new JFrame("Fruit Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        createTopPanel();
        createBottomPanel();

        frame.setVisible(true);
    }
    
    private void initializeGame() { // inicijalizacija igre
        currentFruit = getRandomFruit();
        updateClue();
        updateScoreboard();
        messageArea.setText("");
        currentGuessCount = 0;
    }

    private void createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
 
        clueLabel = new JLabel("Clue: "); // pitanja
        clueLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreboardLabel = new JLabel("Score: 0");
        guessField = new JTextField(20);

        scoreField = new JTextArea(10, 20); // skor
        scoreField.setVisible(false);
        scoreField.setEditable(false);

        playButton = new JButton("Play Game"); // play dugme
        playButton.setPreferredSize(new Dimension(100, 40));
        playButton.addActionListener(e -> startNewGame());

        guessButton = new JButton("Guess"); // guess dugme
        guessButton.setPreferredSize(new Dimension(100, 40));
        guessButton.setEnabled(false);
        guessButton.addActionListener(e -> checkGuess());

        JPanel inputPanel = new JPanel();
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        topPanel.add(clueLabel, BorderLayout.WEST);
        topPanel.add(scoreboardLabel, BorderLayout.EAST);
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(playButton, BorderLayout.SOUTH);
    }

    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mainPanel.add(bottomPanel, BorderLayout.CENTER); // postavljanje pozicje panela u centar

        messageArea = new JTextArea(2, 20); // dimenzije odgovora
        messageArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

    }

    private void startNewGame() { // zapocni novu igru
        guessedFruits.clear();
        fruitDictionary.reset();
        score = 0;
        fruitsGuessed = 0;
        currentClueIndex = 0;
        toggleGameControls(true);
        initializeGame();
    }

    private String getRandomFruit() {
        List<String> availableFruits = new ArrayList<>(fruitDictionary.getFruitNames());
        availableFruits.removeAll(guessedFruits);

        if (availableFruits.isEmpty()) {
            return "All fruits guessed";
        }

        int randomIndex = (int) (Math.random() * availableFruits.size());
        String randomFruit = availableFruits.get(randomIndex);
        guessedFruits.add(randomFruit);
        return randomFruit;
    }

    private void toggleGameControls(boolean isStarting) { // otkriva i sakriva elemente
        playButton.setEnabled(!isStarting);
        guessButton.setEnabled(isStarting);
        guessField.setVisible(isStarting);
        messageArea.setVisible(isStarting);
        scoreField.setVisible(!isStarting);
    }

    private void updateClue() { // Update pitanje
        List<String> clues = fruitDictionary.getCluesForFruit(currentFruit);
        clueLabel.setText(currentClueIndex < clues.size() ? "Clue: " + clues.get(currentClueIndex) : "No more clues available.");
    }

    private void updateScoreboard() { // update scoreboard
        scoreboardLabel.setText("Score: " + score);
    }

    private void checkGuess() { // proveri odgovor
        String userGuess = guessField.getText().trim();
        guessField.setText("");
        
        if (currentGuessCount >= MAX_GUESSES - 1) {
            messageArea.setText("You lost!");
            playButton.setEnabled(true);
            guessButton.setEnabled(false);
            return;
        }
        
        if (userGuess.equalsIgnoreCase(currentFruit)) {
            handleCorrectGuess();
        } else {
            handleIncorrectGuess();
            currentGuessCount++; // Increment the guess count only on incorrect guesses
        }
    }


    private void handleIncorrectGuess() { // procesiranje netacnog odgovra
        List<String> clues = fruitDictionary.getCluesForFruit(currentFruit);
        if (currentClueIndex < clues.size() - 1) {
            currentClueIndex++;
            updateClue();
        } else {
            fruitsGuessed++;
            if (fruitsGuessed >= MAX_GUESSES) { // max_guess = 5 broj maksimalnih pokusaja odgovora
                endGame();
            } else {
                currentFruit = getRandomFruit();
                currentClueIndex = 0;
                updateClue();
            }
        }
        messageArea.setText("Incorrect! Try again.\n");
        updateScoreboard();
    }


    private void handleCorrectGuess() { // procesiranje tacnog odgovora
        score += 10 - currentClueIndex;
        fruitsGuessed++;
        List<String> clues = fruitDictionary.getCluesForFruit(currentFruit);
        messageArea.setText("Correct! " + currentFruit + ".\n\n" + clues.get(clues.size() - 1));

        if (fruitsGuessed >= MAX_GUESSES) {
            endGame();
        } else {
            currentFruit = getRandomFruit();
            currentClueIndex = 0;
            updateClue();
            updateScoreboard();
        }
    }


    private void endGame() { // End the game
        toggleGameControls(false);
        scoreField.setText("Your final score is: " + score);
        scoreField.setVisible(true);
        
        // Center the score field in the frame
        scoreField.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreField.setAlignmentY(Component.CENTER_ALIGNMENT);
        scoreField.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add some space at the top
        
        // Create a new panel to hold the score field
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.add(scoreField, BorderLayout.CENTER);
        
        // Add the score panel to the main panel
        mainPanel.add(scorePanel, BorderLayout.CENTER);
        
        // Refresh the layout
        frame.validate();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(FruitExplorer::new);
    }
}
