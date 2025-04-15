// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * This AI attempts to make columns of cards with the same suit.
 */
public class SuitStacker implements Player {
    @Override
    public int nextMove(Card card, Board board) {
        return card.getSuitNum() -1;
    }
}