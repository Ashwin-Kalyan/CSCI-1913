// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * ConnectFwar is a card game where a player draws cards from a deck
 * and plays them on a board.
 * This is the main game loop that handles the core game logic.
 */
public class ConnectFwar {
    /**
     * Main method to start the game.
     * It creates a new deck and board, and repeatedly draws cards from the deck.
     * The player is prompted to make a move with the drawn card.
     * If the player plays the card successfully, it checks for a win state.
     * If the board is full or the deck is empty, the game ends.
     * @param player - the player who will play the game
     * @return - the score of the player, which is the number of cards remaining in the deck
     */
    public static int play(Player player) {
        Deck deck = new Deck();
        Board board = new Board();

        while (true) { 
            Card card = deck.draw();
            
            if (card == null) {
                return 0; // Deck is empty so the score is 0 and game is over
            }

            int column = player.nextMove(card, board);

            if (column >= 0 && column <= 3 && board.canPlay(card, column)) {
                board.play(card, column);
            
                if (board.isWinState()) {
                    return deck.cardsRemaining();
                }
            } 

            if (isBoardFull(board)) {
                return 0; // Board is full and the game is over
            }
        }
    }

    private static boolean isBoardFull(Board board) {
        for (int col = 0; col < 4; col++) {
            if (board.getTopCard(col) == null || board.getCard(col, 3) == null) return false; // Found an empty column
        }
        return true; // All columns are full
    }
}