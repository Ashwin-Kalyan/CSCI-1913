public class Deck {
    private Card[] deck;
    private int dealt;

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
    
    public void shuffle() {
        for (int i = 0; i < deck.length - 1; i++) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
        dealt = 0; // Reset dealt count after shuffling
    }

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
