package com.videopoker;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Schedule a job to ensure that the GUI is created on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Initialize GameLogic and SwingGUI Here
            GameLogic gameLogic = new GameLogic(new BankrollManager(1000));

            SwingGUI swingGUI = new SwingGUI(gameLogic);
            swingGUI.createAndShowGUI();
        });
    }
}