import java.util.Random;

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