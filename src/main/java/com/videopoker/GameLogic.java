package com.videopoker;

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
}
