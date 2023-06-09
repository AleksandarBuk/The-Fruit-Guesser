package Game;

public class GameStatistics {
    private int totalGames;
    private int totalWins;
    private int totalLosses;
    private int totalPoints;
    private double averagePoints;

    public GameStatistics() {
        totalGames = 0;
        totalWins = 0;
        totalLosses = 0;
        totalPoints = 0;
        averagePoints = 0;
    }

    public void updateStatistics(boolean isWin, int points) {
        totalGames++;
        totalPoints += points;

        if (isWin) {
            totalWins++;
        } else {
            totalLosses++;
        }

        averagePoints = (double) totalPoints / totalGames;
    }

    public void resetStatistics() {
        totalGames = 0;
        totalWins = 0;
        totalLosses = 0;
        totalPoints = 0;
        averagePoints = 0;
    }

    // Getters for the statistics
    public int getTotalGames() {
        return totalGames;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public double getAveragePoints() {
        return averagePoints;
    }
}
