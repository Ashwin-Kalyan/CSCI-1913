// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * Represents the 4x4 game board for ConnectFwar. 
 * Tracks the cards on the board, the rules for valid moves, 
 * and determines when the game has ended.
 */
public class Board {
    private Card[][] board;

    /**
     * Initializes an empty 4x4 board.
     * The board is represented as a 2D array of Card objects.
     * Each cell can hold a Card or be null if empty.
     */
    public Board() {
        board = new Card[4][4];
    }

    /**
     * Gets the highest positioned card of a specified column. 
     * @param column - the column to check (0-3)
     * @return the top card in the specified column, or null if the column is empty
     */
    public Card getTopCard(int column) {
        if (column < 0 || column > 3) return null;
        
        for (int row = 3; row >= 0; row--) {
            if (board[row][column] != null) return board[row][column];
        }
        return null;
    }

    /**
     * Gets the card at a specific position on the board.
     * @param column - the column of the card (0-3)
     * @param row - the row of the card (0-3)
     * @return the card at the specified position, or null if empty
     */
    public Card getCard(int column, int row) {
        if (column < 0 || column > 3 || row < 0 || row > 3) return null;

        if (board[row][column] == null) return null;
        else return board[row][column];
    }

    /**
     * Checks if a card can be played in a specified column.
     * A card can be played if the column is not full,
     * and the card's rank is >= the top card's rank.
     * @param card - the card in question
     * @param column - the column being played (0-3)
     * @return true if the move can be played, false otherwise
     */
    public boolean canPlay(Card card, int column) {
        if (column < 0 || column >= 4) return false;
        
        // Check if column is full
        if (board[3][column] != null) return false;
        
        Card topCard = getTopCard(column);
        // Checks if it is an empty column or if card rank is >= top card's rank
        return topCard == null || card.getRankNum() >= topCard.getRankNum();
    }

    /**
     * Updates the board to show the result of playing a card in a specified column.
     * This method doesn't specify behavior for illegal moves. 
     * @param card - the card being played
     * @param column - the column being played (0-3)
     */
    public void play(Card card, int column) {
        if (column < 0 || column >= 4) return;

        // Find the first empty row in the specified column
        for (int row = 0; row < 4; row++) {
            if (board[row][column] == null) {
                board[row][column] = card;
                break;
            }
        }
    }

    /**
     * Checks if the game is in a win state.
     * A win state is defined as having four connected cards in a row, column, or diagonal.
     * @return true if the game is in a win state, false otherwise
     */
    public boolean isWinState() {
        // Check rows
        for (int row = 0; row < 4; row++) {
            if (checkConnected(board[row][0], board[row][1], board[row][2], board[row][3])) return true;
        }
        
        // Check columns
        for (int col = 0; col < 4; col++) {
            if (checkConnected(board[0][col], board[1][col], board[2][col], board[3][col])) return true;
        }
        
        // Check diagonals
        return checkConnected(board[0][0], board[1][1], board[2][2], board[3][3]) ||
               checkConnected(board[0][3], board[1][2], board[2][1], board[3][0]);
    }

    /**
     * A helper method that checks if all four cards are connected.
     * @param card1 - first card
     * @param card2 - second card
     * @param card3 - third card
     * @param card4 - fourth card
     * @return true if all four cards are connected, false otherwise
     */
    private boolean checkConnected(Card card1, Card card2, Card card3, Card card4) {
        if (card1 == null || card2 == null || card3 == null || card4 == null) return false;
        return areConnected(card1, card2) && areConnected(card2, card3) && areConnected(card3, card4);
    }

    /**
     * Helper method that checks if two cards are connected.
     * Cards are considered connected if they share the same suit or rank,
     * or if their ranks are consecutive (differ by 1).
     * @param card1
     * @param card2
     * @return
     */
    private boolean areConnected(Card card1, Card card2) {
        if (card1 == null || card2 == null) return false;
        return card1.getSuitNum() == card2.getSuitNum() || 
               card1.getRankNum() == card2.getRankNum() || 
               Math.abs(card1.getRankNum() - card2.getRankNum()) == 1;
    }

    /**
     * Returns a string representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------\n");
        // Print from top row to bottom row (row 3 to row 0)
        for (int row = 3; row >= 0; row--) {
            for (int col = 0; col < 4; col++) {
                sb.append("|");
                if (board[row][col] != null) {
                    // Center the card in a 4-character space
                    String cardStr = board[row][col].toFancyString(); // Uses toFancyString() (courtesy of Prof Klver) for formatting
                    sb.append(String.format("%2s", cardStr));
                } else {
                    sb.append("  ");  // Two spaces for empty slot
                }
                sb.append("|");
                // Add space between columns, but not after last column
                if (col < 3) {
                    sb.append(" ");
                }
            }
            // Add newline after each row, except after last row
            if (row > 0) {
                sb.append("\n");
            }
        }
        sb.append("\n------------------\n");
        return sb.toString();
    }
}