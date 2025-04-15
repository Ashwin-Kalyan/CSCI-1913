// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * This interface defines the Player class.
 * It contains a method to get the next move of the player.
 */
public interface Player {
    /**
     * This method returns the next move of the player.
     * @param card - the card that the player has in hand
     * @param board - the board on which the game is being played
     * @return - the next move of the player
     */
    int nextMove(Card card, Board board);
}