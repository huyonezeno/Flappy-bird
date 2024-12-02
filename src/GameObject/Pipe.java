package javaapplication11;

import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.event.KeyEvent;

public class Pipe {
    public int x;
    public int y;
    public static int heightPipe = 300;
    public static int widthPipe = 70;
    public Image img;
    public boolean passed=false;
    public static int startingPipeX = 500;
    public static int startingPipeY = 0;
    private static int velocityX = -4;
    public boolean canAwardPoint = true;
    
    public Pipe(int x, int y,Image img) {
        this.x = x;
        this.y = y;
        this.img =img;
    }
    
    // di chuyển các ccột
    public void  movePipe(){
       x+=velocityX; 
    }
    
}
