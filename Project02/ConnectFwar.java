public class ConnectFwar {
    public static int play(Player player) {
        Deck deck = new Deck();
        Board board = new Board();
        int score = 0;

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