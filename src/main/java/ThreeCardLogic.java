import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.sort;


public class ThreeCardLogic {
    //The method evalHand will return an integer
    //value representing the value of the hand passed in
    public static int evalHand(ArrayList<Card> hand) {
        Boolean straight = false;
        Boolean flush = true;
        Boolean pair = false;
        Boolean threeOfKind = false;


        int val = hand.get(0).getValue();
        if((val > hand.get(1).getValue()) && (val <= hand.get(2).getValue())) {
            Card temp = hand.get(0);
            hand.set(0, hand.get(1));
            hand.set(1, temp);
        }
        else if((val >= hand.get(2).getValue()) && (val <= hand.get(1).getValue())) {
            Card temp = hand.get(2);
            hand.set(2, hand.get(0));
            hand.set(0, temp);
            temp = hand.get(1);
            hand.set(1, hand.get(2));
            hand.set(2, temp);
        }
        else if((val > hand.get(1).getValue()) && (val >= hand.get(2).getValue()) && (hand.get(1).getValue() < hand.get(2).getValue())) {
            Card temp = hand.get(2);
            hand.set(2, hand.get(0));
            hand.set(0,temp);
            temp = hand.get(0);
            hand.set(0, hand.get(1));
            hand.set(1, temp);
        }
        else if((val > hand.get(1).getValue()) && (val >= hand.get(2).getValue()) && (hand.get(1).getValue() > hand.get(2).getValue())) {
            Card temp = hand.get(2);
            hand.set(2, hand.get(0));
            hand.set(0,temp);
        }
        int val1 = hand.get(0).getValue();
        int val2 = hand.get(1).getValue();
        int val3 = hand.get(2).getValue();

        if((val2 == val1+1) && (val3 == val1+2)) {
            straight = true;
        }
        if(val2==val1 || val3==val1 || val3==val2 ) {
            pair = true;
        }
        if(val2==val1 && val3==val1) {
            threeOfKind = true;
        }
        char suit = hand.get(0).getSuit();
        for(int i = 1; i < hand.size();i++) {
            if(hand.get(i).getSuit() != suit) {
                flush = false;
            }
        }
        if(straight && flush) {
            return 1;
        }
        if(threeOfKind) {
            return 2;
        }
        if(straight) {
            return 3;
        }
        if(flush) {
            return 4;
        }
        if(pair) {
            return 5;
        }
        return 0;
    }

    //The method evalPPWinnings will return the amount won for the PairPlus bet. It will
    //evaluate the hand and then evaluate the winnings and return the amount won. If the
    //player lost the Pair Plus bet, it will just return 0.
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int evalHand = evalHand(hand);
        if(evalHand == 5) {
            return bet*2;
        }
        if(evalHand == 4) {
            return bet*4;
        }
        if(evalHand == 3) {
            return bet*7;
        }
        if(evalHand == 2) {
            return bet*31;
        }
        if(evalHand == 1) {
            return bet*41;
        }
        return -bet;
    }

    //Return 0 if dealer has higher card, 1 if player does, 2 if tie.
    private static int highCard(ArrayList<Card> dealer, ArrayList<Card> player) {
        ArrayList<Integer> dealerVals = new ArrayList<>();
        ArrayList<Integer> playerVals = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            dealerVals.add(dealer.get(i).getValue());
            playerVals.add(player.get(i).getValue());
        }
        sort(dealerVals);
        sort(playerVals);
        if(dealerVals.get(2) > playerVals.get(2)) {
            return 0;
        }
        else if(dealerVals.get(2) < playerVals.get(2)) {
            return 1;
        }
        else if(dealerVals.get(1) > playerVals.get(1)) {
            return 0;
        }
        else if(dealerVals.get(1) < playerVals.get(1)) {
            return 1;
        }
        else if(dealerVals.get(0) > playerVals.get(0)) {
            return 0;
        }
        else if(dealerVals.get(0) < playerVals.get(0)) {
            return 1;
        }
        else {
            return 2;
        }
    }

    //The method compareHands will compare the two hands passed in and return an
    //integer based on which hand won, 1(dealer won), 2(player won), 0(Neither won)
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        Boolean higherThanQueen = false;
        for(int i = 0; i < dealer.size(); i++) {
            if(dealer.get(i).getValue() >= 12) {
                higherThanQueen = true;
            }
        }
        if(evalHand(dealer) == 0 && !higherThanQueen) {
            return 0;
        }
        if(evalHand(dealer) == 0 && evalHand(player) != 0) {
            return 2;
        }
        if(evalHand(player) == 0 && evalHand(dealer) != 0) {
            return 1;
        }
        if(evalHand(dealer) < evalHand(player) && evalHand(dealer) != 0 && evalHand(player) != 0) {
            return 1;
        }
        if(evalHand(dealer) > evalHand(player) && evalHand(dealer) != 0 && evalHand(player) != 0) {
            return 2;
        }
        else {
            if(highCard(dealer, player) == 0) {
                return 1;
            }
            if(highCard(dealer, player) == 1) {
                return 2;
            }
            else {
                if(highCard(dealer, player) == 0) {
                    return 1;
                }
                if(highCard(dealer, player) == 1) {
                    return 2;
                }
                else {
                    return 0;
                }
            }
        }
    }
}
