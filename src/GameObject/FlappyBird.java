/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication11;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 500;
    int boardHeight = 500;
    
    Image birdImage;
    Image birdDieImage;
    Image pipeSouthImage;
    Image pipeNorthImage;
    Image foregroundImage;
    Image backgroundImage;
    
    Timer gameLoop;
    Timer placePipeTimer;
    boolean gameOver=false;
    
    //Bird
    int birdWidth = 70;
    int birdHeight = 70;
    int birdX = 200;
    int birdY = 150;
    double rotation =0.0;
    
    //Pipe
    int pipeWidth = 66;
    int pipeHeight = 400;
    ArrayList<Block> pipeArray;
    
    //Physics
    int velocityX = -5;
    int velocityY =0;
    int gravity = 1;
    int jumpStrength = -10;
    Block bird;
    
    class Block{
        int x;
        int y;
        int width;
        int height;
        Image img;

        Block(int x, int y, int width, int height,Image img){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }
    
    //xử lí va chạm cho nền
    
    public FlappyBird(){
        setFocusable(true);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        addKeyListener(this);
        
        birdImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/flappy_bird2.gif")).getImage();
        birdDieImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/bird.png")).getImage();
        pipeSouthImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/pipe-south.png")).getImage();
        pipeNorthImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/pipe-north.png")).getImage();
        foregroundImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/foreground.png")).getImage();
        backgroundImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/background.png")).getImage();
        
        bird = new Block(birdX,birdY,birdWidth,birdHeight,birdImage);
        pipeArray=new ArrayList<>();
        gameLoop = new Timer(1000/60,this);
        gameLoop.start();
    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
//        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
//
    // Vẽ nền và nền đất
    g.drawImage(backgroundImage, 0, 0, null);
    g.drawImage(foregroundImage, 0, 0, null); // Đặt vị trí cho foreground nếu cần
//
//    // Tính toán góc xoay dựa trên vận tốc Y của con chim
//    rotation = Math.toRadians((90 * (velocityY + 20) / 20) - 90);
//    rotation = Math.max(Math.min(rotation, Math.PI / 2), -Math.PI / 2); // Giới hạn góc xoay
//
//    // Tạo AffineTransform để xoay và vẽ con chim
//    AffineTransform transform = new AffineTransform();
//    transform.translate(birdX + birdWidth / 2.0, birdY + birdHeight / 2.0); // Đặt tâm xoay là tâm của con chim
//    transform.rotate(rotation);
//    transform.translate(-birdWidth / 2.0, -birdHeight / 2.0); // Dịch chuyển lại để con chim không bị lệch
//
//    // Vẽ con chim với phép biến đổi
//    g2d.drawImage(birdImage, transform, null); 
    g.drawImage(birdImage, birdX, birdY, birdWidth, birdHeight, null);
    g2d.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }
    
    public void move(){ 
        if (!gameOver) { 
            velocityY += gravity;
            birdY += velocityY;
            bird.y = birdY;  // Cập nhật bird.y theo birdY
        if (birdY >= 370) { // Kiểm tra nếu chim chạm đáy
            birdY = 370; // Cố định vị trí khi chạm nền
            gameOver = true; // Đặt game over nhưng không thay đổi gravity hay velocity
            bird.img=birdDieImage;
        }
        
        }
    }
    public void placePipes(){
        
    }
    public void resetGame() {
    birdX = 200;           // Đặt lại vị trí X của con chim
    birdY = 150;           // Đặt lại vị trí Y của con chim
    velocityY = 0;         // Đặt lại vận tốc Y
    gravity = 1;           // Đặt lại trọng lực (nếu có thay đổi)
    gameOver = false;      // Đặt lại trạng thái gameOver
    bird.img = birdImage;  // Đặt lại hình ảnh của chim
    repaint();             // Vẽ lại màn hình để làm mới giao diện
    gameLoop.start();      // Khởi động lại vòng lặp trò chơi
}
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
       if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) {
                velocityY = jumpStrength;  // Tạo hiệu ứng nhảy khi game chưa kết thúc
            } else {
                resetGame();  // Khởi động lại trò chơi khi game đã kết thúc
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
}

