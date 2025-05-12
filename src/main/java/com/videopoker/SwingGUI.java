package com.videopoker;

import javax.swing.*;
import java.awt.Image;
import java.awt.Component;

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

    // Misc. Hold Button Functions
    public void setHoldButtonStyles(JButton... holdButtons) {
        for (JButton holdButton : holdButtons) {
            holdButton.setBackground(new java.awt.Color(0, 102, 34));
            holdButton.setForeground(java.awt.Color.WHITE);
            holdButton.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));
        }
    }

    public void setHoldButtonStates(JButton... holdButtons) {
        for (JButton holdButton : holdButtons) {
            holdButton.putClientProperty("inactiveFlag", true);
        }
    }

    public void enableHoldButtons(JButton... holdButtons) {
        for (JButton holdButton : holdButtons) {
            holdButton.setEnabled(true);
        }
    }

    public void disableHoldButtons(JButton... holdButtons) {
        for (JButton holdButton : holdButtons) {
            holdButton.setEnabled(false);
        }
    }

    public void resetHoldButtons(JButton... holdButtons) {
        for (JButton holdButton : holdButtons) {
            holdButton.putClientProperty("inactiveFlag", true);
            holdButton.setText("Hold");
        }
    }

    // Fetch and Display Card Image Icons using JLabels and ImageIcons
    private static JLabel createCardLabel(String cardCodeName) {
        /******************************************************************************************
         * Hard-coded File Path Could be Problematic for Portability, Packaging, and
         * Future Changes
         * Probably use classpath-based loading instead
         ******************************************************************************************/
        String imagePath = "src/main/resources/cards/" + cardCodeName + ".png";
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

        // Labels
        JLabel scoreTableLabel = createScoreTableLabel();
        mainPanel.add(scoreTableLabel);

        JLabel currentCreditsLabel = createCurrentCreditsLabel();
        mainPanel.add(currentCreditsLabel);

        JLabel currentBetLabel = createCurrentBetLabel();
        mainPanel.add(currentBetLabel);

        // Bet Managment Buttons
        JButton increaseBetButton = new JButton("Bet +1");
        increaseBetButton.setBackground(new java.awt.Color(0, 102, 34));
        increaseBetButton.setForeground(java.awt.Color.WHITE);
        increaseBetButton.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));

        increaseBetButton.addActionListener(e -> {
            gameLogic.getBankrollManager().increaseCurrentBet();
            currentBetLabel.setText("Current Bet: " + gameLogic.getBankrollManager().getCurrentBet());

            if (gameLogic.getBankrollManager().getCurrentBet() > gameLogic.getBankrollManager().getCurrentCredits()) {
                JOptionPane.showMessageDialog(mainFrame, "Bet cannot exceed current credits", "Error",
                        JOptionPane.ERROR_MESSAGE);
                gameLogic.getBankrollManager().setCurrentBet(gameLogic.getBankrollManager().getCurrentCredits());
                currentBetLabel.setText("Current Bet: " + gameLogic.getBankrollManager().getCurrentBet());
            }
        });

        JButton decreaseBetButton = new JButton("Bet -1");
        decreaseBetButton.setBackground(new java.awt.Color(0, 102, 34));
        decreaseBetButton.setForeground(java.awt.Color.WHITE);
        decreaseBetButton.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));

        decreaseBetButton.addActionListener(e -> {
            gameLogic.getBankrollManager().decreaseCurrentBet();
            currentBetLabel.setText("Current Bet: " + gameLogic.getBankrollManager().getCurrentBet());

            if (gameLogic.getBankrollManager().getCurrentBet() < 0) {
                JOptionPane.showMessageDialog(mainFrame, "Bet cannot be less than 0", "Error",
                        JOptionPane.ERROR_MESSAGE);
                gameLogic.getBankrollManager().setCurrentBet(1); // Reset to minimum bet
                currentBetLabel.setText("Current Bet: " + gameLogic.getBankrollManager().getCurrentBet());
            }
        });

        // Bet Button Layout Positions
        increaseBetButton.setBounds(200, 40, 100, 50);
        mainPanel.add(increaseBetButton);

        decreaseBetButton.setBounds(400, 40, 100, 50);
        mainPanel.add(decreaseBetButton);

        JButton holdButton1 = new JButton("Hold");
        JButton holdButton2 = new JButton("Hold");
        JButton holdButton3 = new JButton("Hold");
        JButton holdButton4 = new JButton("Hold");
        JButton holdButton5 = new JButton("Hold");

        // Hold Button Positions
        holdButton1.setBounds(50, 350, 100, 50);
        holdButton2.setBounds(200, 350, 100, 50);
        holdButton3.setBounds(350, 350, 100, 50);
        holdButton4.setBounds(500, 350, 100, 50);
        holdButton5.setBounds(650, 350, 100, 50);

        mainPanel.add(holdButton1);
        mainPanel.add(holdButton2);
        mainPanel.add(holdButton3);
        mainPanel.add(holdButton4);
        mainPanel.add(holdButton5);

        setHoldButtonStyles(holdButton1, holdButton2, holdButton3, holdButton4, holdButton5);
        setHoldButtonStates(holdButton1, holdButton2, holdButton3, holdButton4, holdButton5);

        // Disable Hold Buttons by Default Until FIRST_DRAW State
        disableHoldButtons(holdButton1, holdButton2, holdButton3, holdButton4, holdButton5);

        // Hold Button Event Listeners
        holdButton1.addActionListener(e -> {
            boolean inactiveFlag = (boolean) holdButton1.getClientProperty("inactiveFlag");
            if (inactiveFlag) {
                holdButton1.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton1.setText("Cancel");
            } else {
                holdButton1.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton1.setText("Hold");
            }
            gameLogic.toggleHoldCards(0);
        });

        holdButton2.addActionListener(e -> {
            boolean inactiveFlag = (boolean) holdButton2.getClientProperty("inactiveFlag");
            if (inactiveFlag) {
                holdButton2.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton2.setText("Cancel");
            } else {
                holdButton2.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton2.setText("Hold");
            }
            gameLogic.toggleHoldCards(1);
        });

        holdButton3.addActionListener(e -> {
            boolean inactiveFlag = (boolean) holdButton3.getClientProperty("inactiveFlag");
            if (inactiveFlag) {
                holdButton3.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton3.setText("Cancel");
            } else {
                holdButton3.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton3.setText("Hold");
            }
            gameLogic.toggleHoldCards(2);
        });

        holdButton4.addActionListener(e -> {
            boolean inactiveFlag = (boolean) holdButton4.getClientProperty("inactiveFlag");
            if (inactiveFlag) {
                holdButton4.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton4.setText("Cancel");
            } else {
                holdButton4.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton4.setText("Hold");
            }
            gameLogic.toggleHoldCards(3);
        });

        holdButton5.addActionListener(e -> {
            boolean inactiveFlag = (boolean) holdButton5.getClientProperty("inactiveFlag");
            if (inactiveFlag == true) {
                holdButton5.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton5.setText("Cancel");
            } else {
                holdButton5.putClientProperty("inactiveFlag", !inactiveFlag);
                holdButton5.setText("Hold");
            }
            gameLogic.toggleHoldCards(4);
        });

        // Deal Button
        JButton dealButton = new JButton("Place Bet");

        dealButton.setBounds(650, 40, 100, 50);
        dealButton.setBackground(new java.awt.Color(0, 102, 34));
        dealButton.setForeground(java.awt.Color.WHITE);
        dealButton.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));

        mainPanel.add(dealButton);

        dealButton.addActionListener(e -> {
            GameLogic.GameState currentGameState = gameLogic.getCurrentState();

            switch (currentGameState) {
                case WAITING_FOR_BET:
                    if (gameLogic.getBankrollManager().getCurrentBet() > 0) {
                        dealButton.setText("Deal");

                        // Take the Player's Bet
                        gameLogic.getBankrollManager().setCurrentBet(gameLogic.getBankrollManager().getCurrentBet());
                        gameLogic.getBankrollManager().takeBet(gameLogic.getBankrollManager().getCurrentBet());

                        // Update Current Credits and Bet Labels
                        gameLogic.getBankrollManager()
                                .setCurrentCredits(gameLogic.getBankrollManager().getCurrentCredits());
                        currentCreditsLabel
                                .setText("Current Credits: " + gameLogic.getBankrollManager().getCurrentCredits());
                        currentBetLabel.setText("Current Bet: " + gameLogic.getBankrollManager().getCurrentBet());

                        // Deal and Display Initial 5-Card Hand
                        gameLogic.dealCards();
                        displayCards(mainPanel);

                        // Allow Players to Edit Hold Cards for This State
                        enableHoldButtons(holdButton1, holdButton2, holdButton3, holdButton4, holdButton5);

                        // Prevent Player from Editing Current Bet
                        increaseBetButton.setEnabled(false);
                        decreaseBetButton.setEnabled(false);

                        gameLogic.setCurrentState(GameLogic.GameState.FIRST_DRAW);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Please place a bet before dealing cards.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case FIRST_DRAW:
                    // Deal and Display Non-Held Cards
                    gameLogic.dealCards();
                    displayCards(mainPanel);
                    
                    // Prevent Player from Editing Held Cards
                    disableHoldButtons(holdButton1, holdButton2, holdButton3, holdButton4, holdButton5);
                    
                    dealButton.setText("Continue");

                    gameLogic.setCurrentState(GameLogic.GameState.FINAL_DRAW);
                    break;
                case FINAL_DRAW:
                    // Is this a Winning Hand?

                    // Payout

                    // Enable Bet Buttons
                    increaseBetButton.setEnabled(true);
                    decreaseBetButton.setEnabled(true);

                    dealButton.setText("Place Bet");

                    // Reset Hold Buttons
                    resetHoldButtons(holdButton1, holdButton2, holdButton3, holdButton4, holdButton5);
                    
                    gameLogic.setCurrentState(GameLogic.GameState.WAITING_FOR_BET);
                    break;
                default:
                    break;
            }
        });
    }

    private void displayCards(JPanel mainPanel) {
        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JLabel && ((JLabel) component).getIcon() != null) {
                mainPanel.remove(component);
            }
        }

        Card[] currentHand = gameLogic.getCurrentHand();
        int cardPosition = 50;

        for (Card card : currentHand) {
            if (card != null) {
                JLabel cardLabel = createCardLabel(card.getCardCodeName());
                cardLabel.setBounds(cardPosition, 150, 100, 150);
                mainPanel.add(cardLabel);
                cardPosition += 150;
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}