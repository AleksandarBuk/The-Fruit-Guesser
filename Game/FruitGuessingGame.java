package Game;

import java.util.Scanner;

public class FruitGuessingGame {
    private FruitGenerator fruitGenerator;
    private Scanner scanner;
    private int score;
    private int incorrectGuesses;
    private String guessResult;

    public FruitGuessingGame() {
        this.fruitGenerator = new FruitGenerator();
        this.scanner = new Scanner(System.in);
        this.score = 0;
        this.incorrectGuesses = 0;
        this.guessResult = "";
    }

    public void playGuess(String guess) {
        int remainingGuesses = 3 - incorrectGuesses; // Calculate the remaining allowed guesses
        String fruit = fruitGenerator.generateRandomFruit();

        if (guess.equalsIgnoreCase(fruit)) {
            System.out.println("Congratulations! You guessed it right.");
            incrementPoints(); // Increment points
            incorrectGuesses = 0; // Reset consecutive incorrect guesses
            guessResult = "Congratulations! You guessed it right.";
            return; // Exit the method after a correct guess
        }

        incorrectGuesses++;
        remainingGuesses--; // Decrement the remaining allowed guesses

        while (remainingGuesses > 0) {
            System.out.println("Incorrect guess. Remaining guesses: " + remainingGuesses);
            System.out.println("Guess the fruit, please:");
            guess = scanner.nextLine().trim();

            if (guess.equalsIgnoreCase(fruit)) {
                System.out.println("Congratulations! You guessed it right.");
                incrementPoints(); // Increment points
                incorrectGuesses = 0; // Reset consecutive incorrect guesses
                guessResult = "Congratulations! You guessed it right.";
                return; // Exit the method after a correct guess
            }

            incorrectGuesses++;
            remainingGuesses--; // Decrement the remaining allowed guesses
        }

        System.out.println("Game over!");
        System.out.println("Your score: " + score);
        scanner.close();
        guessResult = "Game over! Your score: " + score;
    }
    
   
 
    public void resetGame() {
        score = 0;
        incorrectGuesses = 0;
        guessResult = "";
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

    public boolean isGameOver() {
        return incorrectGuesses >= 3;
    }
}
