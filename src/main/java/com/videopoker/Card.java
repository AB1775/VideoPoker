package com.videopoker;

import java.util.List;

public class Card {
    public Object cardValue;
    public String cardSuit;

    // Possible Card Values and Suits: Used an Object to Include Multiple Data Type
    List<Object> cardValuesList = List.of('A', 2, 3, 4, 5,
                                           6, 7, 8, 9, 10,
                                          'J', 'Q', 'K');
    
    List<Character> cardSuitsList = List.of('H', 'D', 'C', 'S');
}
