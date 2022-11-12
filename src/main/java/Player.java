import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;

    public Player() {

    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setAnteBet(int anteBet) {
        this.anteBet = anteBet;
    }

    public int getAnteBet() {
        return anteBet;
    }

    public void setPairPlusBet(int pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }

    public int getPairPlusBet() {
        return pairPlusBet;
    }

    public void setPlayBet(int playBet) {
        this.playBet = playBet;
    }

    public int getPlayBet() {
        return playBet;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }
}
