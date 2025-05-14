package com.videopoker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandEvaluationTest {
    @BeforeEach
    public void resetState() {
        HandEvaluation.resetMaps();
    }

    @Test
    public void testJacksOrBetter() {
        Card[] hand = {
            new Card('J', "D"),
            new Card('J', "S"),
            new Card(2, "H"),
            new Card('Q', "C"),
            new Card(3, "C")
        };

        HandEvaluation.valueCounter(hand);
        String result = HandEvaluation.checkJacksOrBetter();
        assertEquals("Jacks or Better", result);
    }
    
    @Test
    public void testCheckTwoPair() {
        Card[] hand = {
            new Card(2, "D"),
            new Card(2, "H"),
            new Card(7, "S"),
            new Card(7, "D"),
            new Card('J', "C")
        };

        HandEvaluation.valueCounter(hand);
        String result = HandEvaluation.checkTwoPair();
        assertEquals("Two Pair", result);
    }

}