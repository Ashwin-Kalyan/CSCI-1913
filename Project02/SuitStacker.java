public class SuitStacker implements Player {
    @Override
    public int nextMove(Card card, Board board) {
        return card.getSuitNum() -1;
    }
}