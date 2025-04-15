// CSCI 1913 - Project 2
// @author: Ashwin Kalyan

/**
 * Deck class represents a standard deck of 52 playing cards.
 * It provides methods to shuffle the deck, draw cards, 
 * check the number of remaining cards, and check if the deck is empty.
 */
public class Deck {
    private Card[] deck;
    private int dealt; // Number of cards dealt from the deck

    /**
     * Constructor initializes the deck with 52 cards,
     * each represented by a Card object.
     * The deck is shuffled upon creation.
     * The cards are ordered by rank initially.
     */
    public Deck() {
        deck = new Card[52];
        int index = 0;

        for (int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                deck[index] = new Card(rank, suit);
                index++;
            }
        }

        dealt = 0;
        shuffle();
    }
    
    /**
     * Shuffles the deck using the Fisher-Yates shuffle algorithm.
     * This method randomizes the order of the cards in the deck.
     */
    public void shuffle() {
        for (int i = 0; i < deck.length - 1; i++) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }

        dealt = 0; // Reset dealt count after shuffling
    }

    /**
     * Draws a card from the deck by next in line.
     * @return the next card in the deck or null if no cards are left.
     */
    public Card draw() {
        if (dealt < deck.length) {
            return deck[dealt++];
        } else {
            return null; // No more cards to draw
        }
    }

    public int cardsRemaining() {
        return deck.length - dealt;
    }

    public boolean isEmpty() {
        return dealt >= deck.length;
    }
}
