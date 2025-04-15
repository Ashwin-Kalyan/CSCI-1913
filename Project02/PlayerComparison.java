// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * This class compares the performance of different AI players in the ConnectFwar game.
 */
public class PlayerComparison {
    private static final int NUM_GAMES = 10000; // 10K rounds played by each AI

    /**
     * Main method to run the simulation and compare players.
     * It creates instances of different player strategies and runs the game simulation.
     * The average score for each player is calculated and printed.
     */
    public static void main(String[] args) {
        Player randomPlayer = new RandomPlayer();
        Player basicPlayer = new BasicPlayer();
        Player suitStacker = new SuitStacker();
        Player rankRunner = new RankRunner();

        // Run simulations and collect results
        double randomAvg = runSimulation(randomPlayer);
        double basicAvg = runSimulation(basicPlayer);
        double suitAvg = runSimulation(suitStacker);
        double rankAvg = runSimulation(rankRunner);
        
        // Print results with 4 decimal places
        System.out.printf("random %.4f\n", randomAvg);
        System.out.printf("basic %.4f\n", basicAvg);
        System.out.printf("suits %.4f\n", suitAvg);
        System.out.printf("ranks %.4f\n", rankAvg);
    }

    /**
     * Runs a simulation of the ConnectFwar game for a given player.
     * @param player - The player strategy to simulate.
     * @return - The average score of the player after NUM_GAMES rounds.
     */
    private static double runSimulation(Player player) {
        int score = 0;
        for (int i = 0; i < NUM_GAMES; i++) {
            score += ConnectFwar.play(player);
        }
        return (double) score / NUM_GAMES;
    }
}
