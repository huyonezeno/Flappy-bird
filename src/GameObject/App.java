package javaapplication11;

import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 500;
        int boardHeight = 500;

        JFrame frame = new JFrame("Flappy Bird");
        // frame.setVisible(true);       
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game flappyBird = new Game();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus(); 
        frame.setVisible(true);
    }
}
