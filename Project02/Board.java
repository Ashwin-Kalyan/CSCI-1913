public class Board {
    private Card[][] board;

    public Board() {
        board = new Card[4][4];
    }

    public Card getTopCard(int column) {
        if (column < 0 || column > 3) return null;
        
        for (int row = 3; row >= 0; row--) {
            if (board[row][column] != null) return board[row][column];
        }
        return null;
    }

    public Card getCard(int column, int row) {
        if (column < 0 || column > 3 || row < 0 || row > 3) return null;

        if (board[row][column] == null) return null;
        else return board[row][column];
    }

    public boolean canPlay(Card c, int column) {
        if (column < 0 || column >= 4) return false;
        
        // Check if column is full
        if (board[3][column] != null) return false;
        
        Card topCard = getTopCard(column);
        // Can play on empty column or if card rank is >= top card's rank
        return topCard == null || c.getRankNum() >= topCard.getRankNum();
    }

    public void play(Card c, int column) {
        if (column < 0 || column >= 4) return;
        
        for (int row = 0; row < 4; row++) {
            if (board[row][column] == null) {
                board[row][column] = c;
                break;
            }
        }
    }

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

    private boolean checkConnected(Card c1, Card c2, Card c3, Card c4) {
        if (c1 == null || c2 == null || c3 == null || c4 == null) return false;
        return areConnected(c1, c2) && areConnected(c2, c3) && areConnected(c3, c4);
    }

    private boolean areConnected(Card a, Card b) {
        if (a == null || b == null) return false;
        return a.getSuitNum() == b.getSuitNum() || 
               a.getRankNum() == b.getRankNum() || 
               Math.abs(a.getRankNum() - b.getRankNum()) == 1;
    }

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
                    String cardStr = board[row][col].toFancyString();
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