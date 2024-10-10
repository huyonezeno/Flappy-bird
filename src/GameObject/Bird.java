/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;
import java.awt.Rectangle;

public class Bird {
    private float BirdX;
    private float BirdY;
    private Rectangle BirdSize;
    private float g;
    private boolean GameOver;

    // Default constructor
    public Bird() {
        this.GameOver = false;
    }

    // Parameterized constructor
    public Bird(float BirdX, float BirdY, Rectangle BirdSize, float g) {
        this.BirdX = BirdX;
        this.BirdY = BirdY;
        this.BirdSize = BirdSize;
        this.g = g;
        this.GameOver = false;
    }

    // Bird jumps up
    public void Jump() {
        // Logic for jumping
    }

    // Bird moves down
    public void MvDown() {
        // Logic for moving down
    }

    // Set the size of the bird
    public void SetBirdSize() {
        // Logic for setting bird size
    }
}

