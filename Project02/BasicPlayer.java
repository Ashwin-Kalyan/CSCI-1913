// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * BasicPlayer is a simple implementation of the Player interface. 
 * This AI simply plays the first legal choice it sees.
 */
public class BasicPlayer implements Player {
    /**
     * This method determines the next move for the AI.
     * It checks each column in order, returning the first column that
     * can be legally played with the given card.
     * @param card - the card to be played.
     * @param board - the current state of the game board.
     * @return the column index where the card can be played, or -1 if no legal move is found.
     */
    @Override
    public int nextMove(Card card, Board board) {
        for (int col = 0; col < 4; col++) {
            if (board.canPlay(card, col)) return col;
        }
        return -1;
    }
}