
package javaapplication11;

import java.awt.CardLayout;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 500;
        int boardHeight = 500;

        JFrame frame = new JFrame("Flappy Bird");

        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartScreen startScreen = new StartScreen();
        frame.add(startScreen);
        
        startScreen.requestFocus();

        frame.pack();
        frame.setVisible(true);
    }
} 
  