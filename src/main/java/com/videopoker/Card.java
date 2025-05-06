package com.videopoker;

import java.util.List;
import java.util.Random;

public class Card {
    public Object cardValue;
    public String cardSuit;

    // Possible Card Values and Suits: Used an Object to Include Multiple Data Type
    List<Object> cardValuesList = List.of('A', 2, 3, 4, 5,
                                           6, 7, 8, 9, 10,
                                          'J', 'Q', 'K');
    
    List<Character> cardSuitsList = List.of('H', 'D', 'C', 'S');

    // Card Constructor
    public Card(Object cardValue, String cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    // Random Card Generator
    public Card() {
        Random random = new Random();
        this.cardValue = cardValuesList.get(random.nextInt(cardValuesList.size()));
        this.cardSuit = String.valueOf(cardSuitsList.get(random.nextInt(cardSuitsList.size())));
    }

    // Get Card Value
    public Object getCardValue() {
        return cardValue;
    }

    // Get Card Suit
    public String getCardSuit() {
        return cardSuit;
    }

    // Get Card Code Name (i.e. 2-H for 2 of Hearts)
    public String getCardCodeName() {
        return cardValue + "-" + cardSuit;
    }
}
