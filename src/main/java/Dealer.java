import java.util.ArrayList;

public class Dealer {
    private Deck theDeck;
    private ArrayList<Card> dealersHand;

    public Dealer() {
        theDeck = new Deck();
    }

    public void setDealersHand(ArrayList<Card> dealersHand) {
        this.dealersHand = dealersHand;
    }
    public ArrayList<Card> getDealersHand() {
        return dealersHand;
    }

    public void setTheDeck(Deck theDeck) {
        this.theDeck = theDeck;
    }

    public Deck getTheDeck() {
        return theDeck;
    }

    private void checkNumCards(Deck deck) {
        if(deck.getSize() <= 34) {
            theDeck.newDeck();
        }
    }

    //The
    //method dealHand() will return an ArrayList<Card> of three cards removed from
    //theDeck. Before each game starts, the Dealer class must check to see if there are more
    //than 34 cards left in the deck. If not, theDeck must be reshuffled with a new set of 52
    //cards in random order.
    public ArrayList<Card> dealHand() {
        int count = 0;
        ArrayList<Card> hand = new ArrayList<>();
        while(count < 3) {
            hand.add(theDeck.getDeckCards().get(theDeck.getSize()-1));
            theDeck.getDeckCards().remove(theDeck.getSize()-1);
            theDeck.setSize(theDeck.getSize()-1);
            checkNumCards(theDeck);
            count++;
        }
        return hand;

    }
}
