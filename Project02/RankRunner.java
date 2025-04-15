/**
 * This AI attempts to make columns of cards with the same number, 
 * or rows of cards that are increasing/decreasing in value.
 */
public class RankRunner implements Player {
    @Override
    public int nextMove(Card card, Board board) {
        return (card.getRankNum() - 1) % 4; // This will return 0 for all aces, 1 for all twos, 2 for all 3s, 3 for all 4s, 0 for all 5s, 1 for all 6s, etc...
    }
}