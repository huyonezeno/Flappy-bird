/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;

import java.util.Random;

public class Tube {
    private int TubeWidth;
    private int TubeLength;
    private int SpaceBetweenTube;
    private Random Rand;

    // Default constructor
    public Tube() {
    }

    // Parameterized constructor
    public Tube(int TubeWidth, int TubeLength, int Space, Random rand) {
        this.TubeWidth = TubeWidth;
        this.TubeLength = TubeLength;
        this.SpaceBetweenTube = Space;
        this.Rand = rand;
    }

    // Set a new random value for tube positioning
    public void SetRand() {
        // Logic for setting random tube placement
    }
}

