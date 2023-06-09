package Game;

public class FruitGuessingGame {
    private FruitGenerator fruitGenerator;
    private int score;
    private int incorrectGuesses;
    private String guessResult;
    private GameStatistics gameStatistics;

    public FruitGuessingGame() {
        this.fruitGenerator = new FruitGenerator();
        this.score = 0;
        this.incorrectGuesses = 0;
        this.guessResult = "";
        this.gameStatistics = new GameStatistics();
    }

    public void playGuess(String guess) {
        int remainingGuesses = 3 - incorrectGuesses; // Calculate the remaining allowed guesses
        String fruit = fruitGenerator.generateRandomFruit();

        if (guess.equalsIgnoreCase(fruit)) {
            guessResult = "Congratulations! You guessed it right.";
            incrementPoints(); // Increment points
            incorrectGuesses = 0; // Reset consecutive incorrect guesses
            gameStatistics.updateStatistics(true, score);
            return; // Exit the method after a correct guess
        }

        incorrectGuesses++;
        remainingGuesses--; // Decrement the remaining allowed guesses
        gameStatistics.updateStatistics(false, score);

        if (incorrectGuesses >= 3) {
            guessResult = ("Game over! ");
            System.out.println("Your score: " + score);
            return; // Exit the method if the game is over
        }

        guessResult = "Incorrect guess. Remaining guesses: " + remainingGuesses;
    }

    public void resetGame() {
        score = 0;
        incorrectGuesses = 0;
        guessResult = "";
        gameStatistics.resetStatistics();
    }

    public int getScore() {
        return score;
    }

    public void incrementPoints() {
        score++;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public int getRemainingGuesses() {
        return 3 - incorrectGuesses;
    }

    public boolean isGameOver() {
        return incorrectGuesses >= 3;
    }
}
