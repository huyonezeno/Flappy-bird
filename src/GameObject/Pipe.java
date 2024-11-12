/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public int height;
    public int width;
    public Image img;
    public boolean passed=false;

    public Pipe(int x, int y, int width,int height,Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height=height;
        this.img =img;
    }
    
}
