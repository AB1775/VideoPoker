package com.videopoker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    public void testCardConstructor() {
        Card card = new Card();
        
        List<Object> validValues = List.of('A', 2, 3, 4, 5,
                                           6, 7, 8, 9, 10,
                                          'J', 'Q', 'K');
        
        List<String> validSuits = List.of("H", "D", "C", "S");

        assertTrue(validValues.contains(card.getCardValue()), "Invalid card value: " + card.getCardValue());
        assertTrue(validSuits.contains(card.getCardSuit()), "Invalid card suit: " + card.getCardSuit());
    }

    @Test
    public void testRandomCardGenerator_GeneratesValidCard() {
        Card card = new Card();
        assertNotNull(card.getCardValue(), "Returned null card value");
        assertNotNull(card.getCardSuit(), "Returned null card suit");
    }

    @Test
    public void testGetCardCodeName() {
        Card card = new Card("K", "S");
        assertEquals("K-S", card.getCardCodeName(), "Invalid code name: " + card.getCardCodeName());
    }
}