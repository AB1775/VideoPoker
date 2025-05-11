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

    public void CreateAndShowGUI() {
        // JFrame for Main Window
        JFrame mainFrame = new JFrame("Video Poker");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 500);
        mainFrame.setLocationRelativeTo(null); // To Center the Window
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new java.awt.Color(0, 102, 34));
        mainFrame.add(mainPanel);

        // Deal Button
        JButton dealButton = new JButton("Deal");

        dealButton.setBounds(650, 40, 100, 50);
        dealButton.setBackground(new java.awt.Color(0, 102, 34));
        dealButton.setForeground(java.awt.Color.WHITE);
        dealButton.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));
        
        mainPanel.add(dealButton);
        
        dealButton.addActionListener(e -> {
            GameLogic.GameState currentGameState = gameLogic.getCurrentState();

            switch(currentGameState) {
                case WAITING_FOR_BET:
                    if (gameLogic.getBankrollManager().getCurrentBet() > 0) {
                        // Take the Player's Bet
                        gameLogic.getBankrollManager().setCurrentBet(gameLogic.getBankrollManager().getCurrentBet());
                        gameLogic.getBankrollManager().takeBet(gameLogic.getBankrollManager().getCurrentBet());

                        // Update Current Credits and Bet Labels
                        gameLogic.getBankrollManager().setCurrentCredits(gameLogic.getBankrollManager().getCurrentCredits());
                        currentCreditsLabel.setText("Current Credits: " + gameLogic.getBankrollManager().getCurrentCredits());
                        currentBetLabel.setText("Current Bet: " + gameLogic.getBankrollManager().getCurrentBet());

                        // Deal and Display Initial 5-Card Hand
                        gameLogic.dealCards();
                        displayCards(mainPanel);

                        gameLogic.setCurrentState(GameLogic.GameState.FIRST_DRAW);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Please place a bet before dealing cards.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case FIRST_DRAW:
                    gameLogic.setCurrentState(GameLogic.GameState.FINAL_DRAW);
                    break;
                case FINAL_DRAW:
                    gameLogic.setCurrentState(GameLogic.GameState.WAITING_FOR_BET);
                    break;
                default:
                    break;
            } 
        });
    }
}
