/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;

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
    private  boolean isAlive = true;
    private double rotation = 0;
    public double velocityY = 0;
    public double gravity = 1.0;

    public Bird(int x, int y, BufferedImage[] s) {
        this.xBird = x;
        this.yBird = y;
        this.sprites = s;
    }
    // tình trạng của Bird khi còn sống
    public boolean isAlive() {
        return isAlive;
    }
    // tình trạng của Bird khi chết
    public void kill() {
        isAlive = false;
    }
    // đưa Bird về trạng thái sống
    public void reHealth() {
        isAlive = true;
    }

    public void renderBird(Graphics g) {

        // Calculate angle to rotate bird based on y-velocity
        rotation = ((90 * (velocityY + 25) / 25) - 90) * Math.PI / 180;
        // giới hạn góc quay của con chim trong khoảng từ -90 đến 90 độ
        rotation = Math.min(rotation, Math.PI / 2);//nếu góc quay của con chim vượt quá 90 độ thì giữ nguyên ở 90 độ
        rotation = Math.max(rotation, -Math.PI / 2);//nếu góc quay của con chim nhỏ hơn -90 độ thì giữ nguyên ở -90 độ

        // điều chỉnh tốc độ quay của con chim
        double rotationSpeed = 0.8;  
        rotation *= rotationSpeed;  

        if (!isAlive()) {

            // Drop bird on death
            if (yBird < 385) {
                velocityY += gravity;
                yBird += (int) velocityY;
            }

        }
        animate(g, sprites, xBird, yBird, 0.09, rotation);
    }
    // tạo hiệu ứng đạp cánh và con chim xoay theo gia tốc của con chim
    public  void animate(Graphics g, BufferedImage[] sprites, int x, int y, double speed, double angle) {

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform trans = g2d.getTransform();
        AffineTransform at = new AffineTransform();

        // Number of frames
        int count = sprites.length;

        // xoay ảnh quay 1 điểm cố định tại trung tâm của ảnh
        at.rotate(angle, x + 22, y + 15);
        g2d.transform(at);

        // vẽ lên panel bức ảnh được xoay gần nhất
        g2d.drawImage(sprites[(int) (Math.round(currentFrame))], x, y, null);
        // trả về trạng thái ban đầu của ảnh
        g2d.setTransform(trans);
       
        // Switch animation frames
        if(isAlive()){
            if (currentFrame >= count - 1) {
                currentFrame = 0;
            } else {
                currentFrame += speed;
            }
        }

    }

}
