package com.videopoker;

import javax.swing.*;
import java.awt.Image;

public class SwingGUI {
    private static GameLogic gameLogic;

    public SwingGUI(GameLogic gameLogic) {
        SwingGUI.gameLogic = gameLogic;
    }

    // Current Credits Label
    private static JLabel createCurrentCreditsLabel() {
        int currentCredits = gameLogic.getBankrollManager().getCurrentCredits();
        JLabel creditsLabel = new JLabel("Current Credits: " + currentCredits);
        creditsLabel.setBounds(10, 10, 200, 50);
        creditsLabel.setForeground(java.awt.Color.WHITE);
        return creditsLabel;
    }

    // Current Bet Label
    private static JLabel createCurrentBetLabel() {
        int currentBet = gameLogic.getBankrollManager().getCurrentBet();
        JLabel currentBetLabel = new JLabel("Current Bet: " + currentBet);
        currentBetLabel.setBounds(10, 60, 200, 50);
        currentBetLabel.setForeground(java.awt.Color.WHITE);
        return currentBetLabel;
    }

    // Score Table
    private static JLabel createScoreTableLabel() {
        String scoreTableHtml = "<html>" +
                "<style>" +
                "table {border-collapse: collapse; width: 100%;}" +
                "th, td {border: 1px solid white; padding: 8px; text-align: left;}" +
                "th {background-color:rgba(0, 0, 0, 0); color: white;}" +
                "</style>" +
                "<table>" +
                "<tr><th>Hand</th><th>Payout</th></tr>" +
                "<tr><td>Royal Flush</td><td>250x</td></tr>" +
                "<tr><td>Straight Flush</td><td>50x</td></tr>" +
                "<tr><td>Four of a Kind</td><td>25x</td></tr>" +
                "<tr><td>Full House</td><td>9x</td></tr>" +
                "<tr><td>Flush</td><td>6x</td></tr>" +
                "<tr><td>Straight</td><td>4x</td></tr>" +
                "<tr><td>Three of a Kind</td><td>3x</td></tr>" +
                "<tr><td>Two Pair</td><td>2x</td></tr>" +
                "<tr><td>Jacks or Better</td><td>1x</td></tr>" +
                "</table>" +
                "</html>";

        JLabel scoreTableLabel = new JLabel(scoreTableHtml);
        scoreTableLabel.setBounds(800, -25, 200, 500);
        scoreTableLabel.setForeground(java.awt.Color.WHITE);
        return scoreTableLabel;
    }

    // Fetch and Display Card Image Icons using JLabels and ImageIcons
    private static JLabel createCardLabel(String cardCodeName) {
        /******************************************************************************************
         * Hard-coded File Path Could be Problematic for Portability, Packaging, and
         * Future Changes
         * Probably use classpath-based loading instead
         ******************************************************************************************/
        String imagePath = "videopoker/src/main/resources/cards/" + cardCodeName + ".png";
        ImageIcon cardIcon = new ImageIcon(imagePath);

        Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(scaledImage);

        JLabel cardLabel = new JLabel(cardIcon);

        cardLabel.setBounds(50, 150, 100, 150);
        return cardLabel;
    }

    public void createAndShowGUI() {
        
    }
}
