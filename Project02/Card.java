// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * Represents a single playing card.
 * The card has a rank (1-13) and a suit (1-4).
 * The card is immutable.
 */
public class Card {
    private int rank; // 1 -> Ace, 2 -> 2, ..., 10 -> 10, 11 -> Jack, 12 -> Queen, 13 -> King
    private int suit; // 1 -> Spades, 2 -> Hearts, 3 -> Clubs, 4 -> Diamonds

    /**
     * Constructor for Card class.
     * @param rank - the rank of the card (1-13)
     * @param suit - the suit of the card (1-4)
     */
    public Card(int rank, int suit) {
        if ((rank < 1 || rank > 13) || (suit < 1 || suit > 4)) {
            System.out.println("Invalid Card");
            this.rank = 1; // default to Ace if rank invalid
            this.suit = 1; // default to Spades if suit invalid
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

    /**
     * @return - the name of the rank of the card
     */
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

    /**
     * @return - the name of the suit of the card
     */
    public String getSuitName() {
        return switch (suit) {
            case 1 -> "Spades";
            case 2 -> "Hearts";
            case 3 -> "Clubs";
            case 4 -> "Diamonds";
            default -> "Unknown Suit";
        };
    }

    /**
     * @return - the string representation of the card. 
     * The string representation is in the format "Rank of Suit".
     * For example, "Ace of Spades", "Two of Hearts", etc.
     */
    @Override
    public String toString() {
        return getRankName() + " of " + getSuitName();
    }
    
    /**
     * Checks if two cards are equal.
     * Two cards are equal if they have the same rank and suit.
     * @param obj - the object to compare to
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return rank == card.rank && suit == card.suit;
    }

    /**
     * Fancy display utility courtesy of Professor Kluver.
     * @return a string representation of the card in a fancy format.
     */
    public String toVeryFancyString() {
        // we're getting into some deep magic with this one.
        // Don't be too worried if it doesn't work on your computer.

        
        // user getSuitNum and getRankNum so we don't assume any specific private variable name.
        char letter_part_one = '\uD83C';
        char letter_part_two = (char) ('\uDCA0' + getRankNum());
        if (getSuitNum() == 2) {
            letter_part_two += 16;
        } else if (getSuitNum() == 4) {
            letter_part_two += 32;
        } else if (getSuitNum() == 3) {
            letter_part_two += 48;
        }
        if (getRankNum() >=12) {
            letter_part_two++; // skip "Knight".
        }
        
        String retval;
        if (getSuitNum()%2 == 1) {
            // black card
            retval = "\u001B[30m\u001B[47m";
        } else {
            // red card
            retval = "\u001B[31m\u001B[47m";
        }

        return retval + letter_part_one + letter_part_two + " \u001B[0m";
    }

    /**
     * Display utility courtesy of Professor Kluver.
     * @return a string representation of the card in a fancy format.
     */
    public String toFancyString() {
        String suits = "♠♥♣♦";
        String ranks = "A23456789⑩JQK";
        // user getSuitNum and getRankNum so we don't assume any specific private variable name.
        return ""+suits.charAt(getSuitNum()-1) + ranks.charAt(getRankNum()-1);
    }
}