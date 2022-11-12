import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class ThreeCardLogicTest extends ThreeCardLogic {

    @Test
    void testOne() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 5));
        hand.add(new Card('D', 6));
        hand.add(new Card('C', 4));
        assertEquals(evalHand(hand),3);
    }

    @Test
    void testTwo() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 8));
        hand.add(new Card('D', 8));
        hand.add(new Card('S', 8));
        assertEquals(evalHand(hand),2);
    }

    @Test
    void testThree() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 8));
        hand.add(new Card('C', 9));
        hand.add(new Card('C', 10));
        assertEquals(evalHand(hand),1);
    }

    @Test
    void testFour() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 8));
        hand.add(new Card('C', 3));
        hand.add(new Card('C', 12));
        assertEquals(evalHand(hand),4);
    }

    @Test
    void testFive() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 3));
        hand.add(new Card('D', 8));
        hand.add(new Card('S', 3));
        assertEquals(evalHand(hand),5);
    }

    @Test
    void testSix() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 3));
        hand.add(new Card('C', 4));
        hand.add(new Card('S', 3));
        assertEquals(evalHand(hand),5);
    }

    @Test
    void testSeven() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('H', 5));
        hand.add(new Card('H', 7));
        hand.add(new Card('D', 4));
        assertEquals(evalHand(hand),0);
    }

    @Test
    void testEight() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 2));
        hand.add(new Card('D', 3));
        hand.add(new Card('C', 4));
        assertEquals(evalPPWinnings(hand, 4),28);
    }

    @Test
    void testNine() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 8));
        hand.add(new Card('D', 8));
        hand.add(new Card('S', 8));
        assertEquals(evalPPWinnings(hand, 3),93);
    }

    @Test
    void testTen() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 2));
        hand.add(new Card('H', 2));
        hand.add(new Card('D', 2));
        assertEquals(evalPPWinnings(hand, 2),62);
    }

    @Test
    void testEleven() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 8));
        hand.add(new Card('C', 9));
        hand.add(new Card('C', 10));
        assertEquals(evalPPWinnings(hand,2),82);
    }

    @Test
    void testTwelve() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 5));
        hand.add(new Card('C', 2));
        hand.add(new Card('C', 11));
        assertEquals(evalPPWinnings(hand,5),20);
    }

    @Test
    void testThirteen() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 3));
        hand.add(new Card('C', 4));
        hand.add(new Card('S', 4));
        assertEquals(evalPPWinnings(hand,5),10);
    }

    @Test
    void testFourteen() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 11));
        hand.add(new Card('C', 7));
        hand.add(new Card('S', 4));
        assertEquals(evalPPWinnings(hand,5),-5);
    }

    @Test
    void testFifteen() {
        ArrayList<Card> dealer= new ArrayList<>();
        dealer.add(new Card('C', 10));
        dealer.add(new Card('C', 11));
        dealer.add(new Card('C', 12));
        ArrayList<Card> player = new ArrayList<>();
        player.add(new Card('C', 11));
        player.add(new Card('C', 7));
        player.add(new Card('S', 11));
        assertEquals(compareHands(dealer,player),1);
    }

    @Test
    void testSixteen() {
        ArrayList<Card> dealer= new ArrayList<>();
        dealer.add(new Card('S', 11));
        dealer.add(new Card('C', 11));
        dealer.add(new Card('C', 12));
        ArrayList<Card> player = new ArrayList<>();
        player.add(new Card('C', 11));
        player.add(new Card('D', 11));
        player.add(new Card('S', 11));
        assertEquals(compareHands(dealer,player),2);
    }

    @Test
    void testSeventeen() {
        ArrayList<Card> dealer= new ArrayList<>();
        dealer.add(new Card('S', 12));
        dealer.add(new Card('H', 11));
        dealer.add(new Card('C', 12));
        ArrayList<Card> player = new ArrayList<>();
        player.add(new Card('H', 12));
        player.add(new Card('D', 11));
        player.add(new Card('D', 12));
        assertEquals(compareHands(dealer,player),0);
    }

    @Test
    void testEighteen() {
        ArrayList<Card> dealer= new ArrayList<>();
        dealer.add(new Card('S', 8));
        dealer.add(new Card('S', 9));
        dealer.add(new Card('S', 10));
        ArrayList<Card> player = new ArrayList<>();
        player.add(new Card('H', 12));
        player.add(new Card('S', 12));
        player.add(new Card('D', 12));
        assertEquals(compareHands(dealer,player),0);
    }

    @Test
    void testNineteen() {
        ArrayList<Card> dealer= new ArrayList<>();
        dealer.add(new Card('D', 12));
        dealer.add(new Card('S', 9));
        dealer.add(new Card('S', 12));
        ArrayList<Card> player = new ArrayList<>();
        player.add(new Card('D', 10));
        player.add(new Card('S', 9));
        player.add(new Card('H', 10));
        assertEquals(compareHands(dealer,player),1);
    }

    @Test
    void testTwenty() {
        ArrayList<Card> dealer= new ArrayList<>();
        dealer.add(new Card('D', 11));
        dealer.add(new Card('S', 10));
        dealer.add(new Card('S', 12));
        ArrayList<Card> player = new ArrayList<>();
        player.add(new Card('D', 9));
        player.add(new Card('D', 10));
        player.add(new Card('H', 11));
        assertEquals(compareHands(dealer,player),1);
    }

    @Test
    void testTwentyOne() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 5));
        hand.add(new Card('D', 3));
        hand.add(new Card('C', 4));
        assertEquals(evalHand(hand),3);
    }

    @Test
    void testTwentyTwo() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 5));
        hand.add(new Card('D', 4));
        hand.add(new Card('C', 3));
        assertEquals(evalHand(hand),3);
    }

    @Test
    void testTwentyThree() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card('C', 3));
        hand.add(new Card('D', 4));
        hand.add(new Card('C', 5));
        assertEquals(evalHand(hand),3);
    }


}
