package com.videopoker;

import java.util.Map;
import java.util.HashMap;

public class BankrollManager {
    private int currentCredits;
    private int currentBet;

    Map<String, Integer> payoutMap = new HashMap<>( // Make Immutable
        Map.of(
            "Royal Flush", 250,
            "Straight Flush", 50,
            "Four of a Kind", 25,
            "Full House", 9,
            "Flush", 6,
            "Straight", 4,
            "Three of a Kind", 3,
            "Two Pair", 2,
            "Jacks or Better", 1
        )
    );

    // Constructor
    public BankrollManager(int initialCredits) {
        this.currentCredits = initialCredits;
        this.currentBet = 0;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentCredits(int credits) {
        this.currentCredits = credits;
    }

    public void setCurrentBet(int bet) {
        this.currentBet = bet;
    }

    // Take the Player's Bet and Subtract from Current Credits
    public void takeBet(int bet) {
        this.currentBet = bet;
        currentCredits = currentCredits - bet;
        setCurrentCredits(currentCredits);
    }

    // Payout Calculation
    public int calculatePayout(String hand) {
        if (payoutMap.containsKey(hand)) {
            return payoutMap.get(hand) * currentBet;
        }
        return 0; // No payout for Loss
    }

    // Update the Bankroll Following Round Completion
    public void updateCurrentCredits(int amount) {
        this.currentCredits += amount;
    }

    // Increase Current Bet by One
    public void increaseCurrentBet() {
        // Logic to ++currentBet when Increase Bet Button is Clicked
        ++currentBet;
    }
    // Decrease Current Bet by One
    public void decreaseCurrentBet() {
        // Logic to --currentBet when Decrease Bet Button is Clicked
        --currentBet;
    }
}
