public class Card {
    private int rank; // 1 -> Ace, 2 -> 2, ..., 10 -> 10, 11 -> Jack, 12 -> Queen, 13 -> King
    private int suit; // 1 -> Spades, 2 -> Hearts, 3 -> Clubs, 4 -> Diamonds

    public Card(int rank, int suit) {
        if ((rank < 1 || rank > 13) || (suit < 1 || suit > 4)) {
            System.out.println("Invalid Card");
            this.rank = 1;
            this.suit = 1;
        } else {
            this.rank = rank;
            this.suit = suit;
        }
    }

    public int getRankNum() {
        return rank;
    }

    public int getSuitNum() {
        return suit;
    }

    public String getRankName() {
        return switch (rank) {
            case 1 -> "Ace";
            case 2 -> "Two";
            case 3 -> "Three";
            case 4 -> "Four";
            case 5 -> "Five";
            case 6 -> "Six";
            case 7 -> "Seven";
            case 8 -> "Eight";
            case 9 -> "Nine";
            case 10 -> "Ten";
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            default -> "Unknown Rank";
        };
    }

    public String getSuitName() {
        return switch (suit) {
            case 1 -> "Spades";
            case 2 -> "Hearts";
            case 3 -> "Clubs";
            case 4 -> "Diamonds";
            default -> "Unknown Suit";
        };
    }

    @Override
    public String toString() {
        return getRankName() + " of " + getSuitName();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return rank == card.rank && suit == card.suit;
    }
}
