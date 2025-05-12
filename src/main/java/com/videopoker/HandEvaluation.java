package com.videopoker;

import java.util.Map;
import java.util.HashMap;

public class HandEvaluation {

    // Tracks the count of each card value (e.g., "2", "3", "A", "K") 
    static Map<String, Integer> valueCounterMap = new HashMap<>();
    static {
        valueCounterMap.put("2", 0);
        valueCounterMap.put("3", 0);
        valueCounterMap.put("4", 0);
        valueCounterMap.put("5", 0);
        valueCounterMap.put("6", 0);
        valueCounterMap.put("7", 0);
        valueCounterMap.put("8", 0);
        valueCounterMap.put("9", 0);
        valueCounterMap.put("10", 0);
        valueCounterMap.put("A", 0);
        valueCounterMap.put("J", 0);
        valueCounterMap.put("Q", 0);
        valueCounterMap.put("K", 0);
    }

    // Tracks the count of each card suit (e.g., 'H' for Hearts, 'D' for Diamonds, 'C' for Clubs, 'S' for Spades)
    static Map<Character, Integer> suitCounterMap = new HashMap<>();
    static {
        suitCounterMap.put('H', 0);
        suitCounterMap.put('D', 0);
        suitCounterMap.put('C', 0);
        suitCounterMap.put('S', 0);
    }

    public static void valueCounter(Card[] currentHand) {
        for (Card card : currentHand) {
            String cardValueString = card.getCardValue().toString();
            valueCounterMap.merge(cardValueString, 1, Integer::sum);
        }
    }

    public static void suitCounter(Card[] currentHand) {
        for (Card card : currentHand) {
            suitCounterMap.merge(card.getCardSuit().charAt(0), 1, Integer::sum);
        }
    }

    // Hand Checking Functions
    // Returns one of the Strings (Key) from payoutMap and Passes it to calculatePayout(String)
    // Will be Called in Descending Order from Strongest Hand to Weakest Hand
    // Return Empty String for Non-Matching Hands
    public static String checkRoyalFlush() { return ""; }
   
    public static String checkStraightFlush() { return ""; }
    
    public static String checkFlush() { return ""; }
    
    public static String checkFourOfAKind() { return ""; }
    
    public static String checkFullHouse() { return ""; }
    
    public static String checkStraight() { return ""; }
    
    public static String checkThreeOfAKind() { return ""; }
    
    public static String checkTwoPair() { return ""; }

    public static String checkJacksOrBetter() { return ""; }

}
