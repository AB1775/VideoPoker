package com.videopoker;

import java.util.Map;
import java.util.HashMap;

public class BankrollManager {
    public int currentCredits;
    public int currentBet;
    
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
}
