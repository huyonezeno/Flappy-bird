
package javaapplication11;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Bird {

    public int xBird;
    public int yBird;
    public final int widthBird = 45;
    public final int heightBird = 32;
    public int jumpStrength = -10;
    private int startingBirdX = 200;
    private int startingBirdY = 150;
    private BufferedImage[] sprites;
    private static double currentFrame = 0;
    private boolean isAlive = true;
    private double rotation = 0;
    public double velocityY = 0;
    public double gravity = 1.0;
    
    public Bird(int x, int y, BufferedImage[] s) {
        this.xBird = x;
        this.yBird = y;
        this.sprites = s;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }
    
    public void setGameStart(){
        xBird = startingBirdX;
        yBird = startingBirdY;
    }
     public void reHealth(){
         isAlive = true;
     }
    public  void renderBird(Graphics g) {

        // Calculate angle to rotate bird based on y-velocity
        rotation = ((90 * (velocityY + 25) / 25) - 90) * Math.PI / 180;
        
        rotation = Math.min(rotation, Math.PI / 2);
        rotation = Math.max(rotation, -Math.PI / 2);
        
        // Increase the speed of rotation (adjust this factor for faster rotation)
        double rotationSpeed = 0.8;  // Factor to increase rotation speed
        rotation *= rotationSpeed;  // Apply the rotation speed factor
        
        if (!isAlive()) {

			// Drop bird on death
			if (yBird < 385) {
				velocityY += gravity;
				yBird += (int) velocityY;
			}

	}
        animate(g, sprites, xBird, yBird, 0.09, rotation);
    }

    public static void animate(Graphics g, BufferedImage[] sprites, int x, int y, double speed, double angle) {

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform trans = g2d.getTransform();
        AffineTransform at = new AffineTransform();

        // Number of frames
        int count = sprites.length;

        // Rotate the image
        at.rotate(angle, x + 25, y + 25);
        g2d.transform(at);

        // Draw the current rotated frame
        g2d.drawImage(sprites[(int) (Math.round(currentFrame))], x, y, null);

        g2d.setTransform(trans);

        // Switch animation frames
        if (currentFrame >= count-1) {
            currentFrame = 0;
        } else {
            currentFrame += speed;
        }

    }
    
}

