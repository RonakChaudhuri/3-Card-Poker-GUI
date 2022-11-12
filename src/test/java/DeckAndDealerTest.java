import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckAndDealerTest {

    static Deck deckOne;
    static Dealer dealerOne;
    static Dealer dealerTwo;
    static Dealer dealerThree;

    @BeforeAll
    static void setup() {
        deckOne = new Deck();
        dealerOne = new Dealer();
        dealerTwo = new Dealer();
        dealerThree = new Dealer();
    }

    @Test
    void testOne() {
        Card cardOne = deckOne.getDeckCards().get(0);
        for(int i = 1; i < deckOne.getSize(); i++) {
            if(deckOne.getDeckCards().get(i).getValue() == cardOne.getValue()) {
                if(deckOne.getDeckCards().get(i).getSuit() == cardOne.getSuit()) {
                    fail("Duplicate Card Found");
                }
            }
            //System.out.println(deckOne.getDeckCards().get(i).getValue());
        }
    }

    @Test
    void testTwo() {
        assertEquals(52, deckOne.getSize());
    }

    @Test
    void testThree() {
        Card c = new Card('C', (int)(Math.random()*12+2));
        Boolean found = false;
        for(int i = 0; i < deckOne.getSize(); i++) {
            if(deckOne.getDeckCards().get(i).getValue() == c.getValue()) {
                if(deckOne.getDeckCards().get(i).getSuit() == c.getSuit()) {
                    found = true;
                }
            }
        }
        assertTrue(found);
    }

    @Test
    void testFour() {
        Card cardOne = deckOne.getDeckCards().get(0);
        deckOne.newDeck();
        assertNotEquals(cardOne, deckOne.getDeckCards().get(0));
    }

    @Test
    void testFive() {
        assertEquals(52, dealerOne.getTheDeck().getSize());
    }

    @Test
    void testSix() {
        ArrayList<Card> hand = new ArrayList<>();
        hand = dealerTwo.dealHand();
        assertEquals(3, hand.size());
        dealerTwo.setDealersHand(hand);
        assertEquals(hand,dealerTwo.getDealersHand());
    }

    @Test
    void testSeven() {
        ArrayList<Card> hand = new ArrayList<>();
        int count = 52;
        while(dealerThree.getTheDeck().getSize()>37) {
            dealerThree.dealHand();
            count -= 3;
            assertEquals(dealerThree.getTheDeck().getSize(), count);
        }
        dealerThree.dealHand();
        assertEquals(dealerThree.getTheDeck().getSize(), 52);
    }

    @Test
    void testEight() {
        deckOne.newDeck();
        assertEquals(52, deckOne.getSize());
    }

    @Test
    void testNine() {
        deckOne.newDeck();
        Card cardOne = deckOne.getDeckCards().get(0);
        for(int i = 1; i < deckOne.getSize(); i++) {
            if(deckOne.getDeckCards().get(i).getValue() == cardOne.getValue()) {
                if(deckOne.getDeckCards().get(i).getSuit() == cardOne.getSuit()) {
                    fail("Duplicate Card Found");
                }
            }
        }
    }

    @Test
    void testTen() {
        deckOne.newDeck();
        Card c = new Card('C', (int)(Math.random()*12+2));
        Boolean found = false;
        for(int i = 0; i < deckOne.getSize(); i++) {
            if(deckOne.getDeckCards().get(i).getValue() == c.getValue()) {
                if(deckOne.getDeckCards().get(i).getSuit() == c.getSuit()) {
                    found = true;
                }
            }
        }
        assertTrue(found);
    }




}
