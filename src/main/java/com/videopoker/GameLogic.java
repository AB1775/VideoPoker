package com.videopoker;

import java.util.List;
import java.util.ArrayList;

public class GameLogic {
    /* Game State Restrictions
     *  WAITING_FOR_BET: Player Must Place Valid Bet Before Initial Hand is Dealt
     *  FIRST_DRAW: Player Must Hold 0-5 Cards, No Bets Placed, 1 Draw Remaining, No Payout
     *  FINAL_DRAW: Nonheld Cards are Replaced, Player Cannot Hold or Unhold Cards from This Point, No Bets are Placed, 0 Draws Remain
     * ** ENSURE THAT STATE TRANSITIONS ARE VALID **
     */
    public enum GameState {
        WAITING_FOR_BET,          // Player Places a Bet
        FIRST_DRAW,               // Initial 5-Card Hand is Dealt
        FINAL_DRAW,               // Player Has Chosen Hold Cards and is Dealt New Cards Hand is Evaluated and Bankroll is Updated
    }

    private GameState currentState;
    private BankrollManager bankrollManager;
    private Card[] currentHand;
    private List<Integer> heldCards;

    public GameLogic(BankrollManager bankrollManager) {
        this.bankrollManager = bankrollManager;
        this.currentHand = new Card[5];
        this.heldCards = new ArrayList<>();
        this.currentState = GameState.WAITING_FOR_BET;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState state) {
        this.currentState = state;
    }

    public Card[] getCurrentHand() {
        return currentHand;
    }

    public List<Integer> getHeldCards() {
        return heldCards;
    }
 
    public void toggleHoldCards(int cardIndex) {
        if (heldCards.contains(cardIndex)) {
            heldCards.remove(Integer.valueOf(cardIndex));
        } else {
            heldCards.add(cardIndex);
        }
}

    public void dealCards() {
        for (int i = 0; i < currentHand.length; ++i) {
            if (!heldCards.contains(i)) {
                currentHand[i] = new Card();
            }
        }
        heldCards.clear();
    }

    public BankrollManager getBankrollManager() {
        return bankrollManager;
    }
}
