public final class Deck {
    private Card[] deck;
    private int dealt;

    public Deck() {
        deck = new Card[52];
        int index = 0;
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                deck[index] = new Card(rank, suit);
                index++;
            }
        }
        dealt = -1;
        shuffle();
    }
    
    public void shuffle() {
        for (int i = 0; i < deck.length; i++) {
            int j = (int) (Math.random() * deck.length);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    public Card draw() {
        if (dealt < deck.length) {
            return deck[dealt++];
        } else {
            return null; // No more cards to draw
        }
    }

    public int cardsRemaining() {
        return deck.length - dealt - 1;
    }

    public boolean isEmpty() {
        return dealt >= deck.length - 1;
    }
}
