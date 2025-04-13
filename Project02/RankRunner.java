public class RankRunner implements Player {
    @Override
    public int nextMove(Card card, Board board) {
        return (card.getRankNum() - 1) % 4;
    }
}