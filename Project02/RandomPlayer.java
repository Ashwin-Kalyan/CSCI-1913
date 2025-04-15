// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

import java.util.Random;

/**
 * This AI acts randomly in the game.
 */
public class RandomPlayer implements Player {
    private Random random;

    public RandomPlayer() {
        this.random = new Random();
    }

    @Override
    public int nextMove(Card card, Board board) {
        return random.nextInt(4);
    }
}