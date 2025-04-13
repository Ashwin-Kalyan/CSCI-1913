public class BasicPlayer implements Player {
    @Override
    public int nextMove(Card card, Board board) {
        for (int col = 0; col < 4; col++) {
            if (board.canPlay(card, col)) return col;
        }
        return -1;
    }
}