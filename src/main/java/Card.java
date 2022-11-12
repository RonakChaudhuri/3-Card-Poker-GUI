public class Card {
    private char suit;
    private int value;

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }
}
