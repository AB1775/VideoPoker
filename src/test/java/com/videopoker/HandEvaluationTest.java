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
}