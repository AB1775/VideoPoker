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

    @Test
    public void testCheckThreeOfAKind() {
        Card[] hand = {
            new Card('A', "H"),
            new Card(5, "D"),
            new Card(5, "S"),
            new Card (5, "C"),
            new Card(2, "D")
        };

        HandEvaluation.valueCounter(hand);
        String result = HandEvaluation.checkThreeOfAKind();
        assertEquals("Three of a Kind", result);
    }

    @Test
    public void testCheckFourOfAKind() {
        Card[] hand = {
            new Card('A', "H"),
            new Card('A', "D"),
            new Card('A', "S"),
            new Card ('A', "C"),
            new Card(2, "D")
        };

        HandEvaluation.valueCounter(hand);
        String result = HandEvaluation.checkFourOfAKind();
        assertEquals("Four of a Kind", result);
    }

    @Test
    public void testCheckFlush() {
        Card[] hand = {
            new Card(3, "H"),
            new Card(2, "H"),
            new Card('A', "H"),
            new Card (5, "H"),
            new Card(7, "H")
        };

        HandEvaluation.suitCounter(hand);
        String result = HandEvaluation.checkFlush();
        assertEquals("Flush", result);
    }
}