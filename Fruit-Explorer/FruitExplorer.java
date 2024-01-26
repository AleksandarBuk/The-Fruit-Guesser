import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;  // Import ArrayList
import java.util.Map;

public class FruitExplorer {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel clueLabel;
    private JLabel scoreboardLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea messageArea;

    private FruitDictionary fruitDictionary;
    private String currentFruit;
    private int currentClueIndex;
    private int score;

    public FruitExplorer() {
        frame = new JFrame("Fruit Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // turn resizeable off
        frame.setSize(800, 600); // app window size
        frame.setLocationRelativeTo(null); // sets app window to the center of the screen

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));

        clueLabel = new JLabel("Clue: ");
        mainPanel.add(clueLabel);

        scoreboardLabel = new JLabel("Score: " + score);
        mainPanel.add(scoreboardLabel);

        guessField = new JTextField();
        mainPanel.add(guessField);

        guessButton = new JButton("Guess");
        mainPanel.add(guessButton);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        mainPanel.add(new JScrollPane(messageArea));

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.setVisible(true);

        fruitDictionary = new FruitDictionary();
        initializeGame();
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
        List<String> fruitNames = new ArrayList<>(fruitDictionary.getFruitNames());  // Use getFruitNames method
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
