public class PlayerComparison {
    private static final int NUM_GAMES = 10000;

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

    private static double runSimulation(Player player) {
        int score = 0;
        for (int i = 0; i < NUM_GAMES; i++) {
            score += ConnectFwar.play(player);
        }
        return (double) score / NUM_GAMES;
    }
}
