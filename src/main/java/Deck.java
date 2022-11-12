import java.util.ArrayList;

public class Deck extends ArrayList<Card> {
    private ArrayList<Card> deckCards = new ArrayList<>();
    private int size = 0;

    private void randomizeCards() {
        for(int i = 0; i < 52; i++) {
            int num = (int)(Math.random() * 51);
            Card temp = deckCards.get(i);
            deckCards.set(i,deckCards.get(num));
            deckCards.set(num,temp);
        }
    }

    //The constructor will
    //create a new deck of 52 cards that have been sorted in random order
    public Deck() {
        for(int i = 2; i <= 14; i++) {
            deckCards.add(new Card('C', i));
            deckCards.add(new Card('D', i));
            deckCards.add(new Card('S', i));
            deckCards.add(new Card('H', i));
        }
        randomizeCards();
        size = deckCards.size();
    }

    //clear all the cards and create a brand new deck of 52 cards sorted in
    //random order
    public void newDeck() {
        deckCards.clear();
        size = 0;
        for(int i = 2; i <= 14; i++) {
            deckCards.add(new Card('C', i));
            deckCards.add(new Card('D', i));
            deckCards.add(new Card('S', i));
            deckCards.add(new Card('H', i));
        }
        randomizeCards();
        size = deckCards.size();
    }

    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
}
