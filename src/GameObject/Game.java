package javaapplication11;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.InputStream;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game extends JPanel implements ActionListener, KeyListener, MouseListener {

    // kích thước của JPanel
    int boardWidth = 500;
    int boardHeight = 500;

    // các ảnh đồ họa cần thiết trong game
    BufferedImage birdImage[] = new BufferedImage[3];
    BufferedImage bottomPipeImage;
    BufferedImage topPipeImage;
    BufferedImage foregroundImage;
    BufferedImage backgroundImage;
    BufferedImage gameOverLabel;
    BufferedImage restartButton;
    BufferedImage addToLeaderBoardButton;
    BufferedImage scoreBoard;
    BufferedImage score0;
    BufferedImage score1;
    BufferedImage score2;
    BufferedImage score3;
    BufferedImage score4;
    BufferedImage score5;
    BufferedImage score6;
    BufferedImage score7;
    BufferedImage score8;
    BufferedImage score9;
    BufferedImage goldMedal;
    BufferedImage sliverMedal;
    BufferedImage bronzeMedal;
    BufferedImage platiumMedal;
    // vòng lặp
    Timer gameLoop;
    Timer placePipeTimer;
    private int score = 0;
    private int bestScore = 0;
    private String medal;
    private static Audio audio = new Audio();

    //Bird
    Bird bird;
    
    private int distanceScore = 0;
    //Pipe
    ArrayList<Pipe> pipes;
    Random random = new Random();

    //font
    private Font flappyFontBase, flappyFontReal, flappyScoreFont, flappyMiniFont = null;
    private Point clickedPoint = new Point(-1, -1);
    
    //Game State
    final static int MENU = 0;
    final static int GAME = 1;
    private int gameState = MENU;

    public Game() throws IOException {
        setFocusable(true);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        addKeyListener(this);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\flappy-font.ttf"));
            flappyFontBase = Font.createFont(Font.TRUETYPE_FONT, is);

            // Header and sub-header fonts
            flappyScoreFont = flappyFontBase.deriveFont(Font.PLAIN, 50);
            flappyFontReal = flappyFontBase.deriveFont(Font.PLAIN, 20);
            flappyMiniFont = flappyFontBase.deriveFont(Font.PLAIN, 15);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        birdImage[0] = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\yellowBird1.png"));
        birdImage[1] = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\yellowBird2.png"));
        birdImage[2] = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\yellowBird3.png"));
        topPipeImage = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\pipe-south.png"));
        bottomPipeImage = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\pipe-north.png"));
        foregroundImage = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\foreground.png"));
        backgroundImage = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\background.png"));
        restartButton = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\restart.png"));
        gameOverLabel = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\gameOverText.png"));
        addToLeaderBoardButton = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\addtoleaderboard.png"));
        scoreBoard = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\scoreCard.png"));
        score0 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\0.png"));
        score1 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\1.png"));
        score2 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\2.png"));
        score3 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\3.png"));
        score4 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\4.png"));
        score5 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\5.png"));
        score6 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\6.png"));
        score7 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\7.png"));
        score8 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\8.png"));
        score9 = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\9.png"));
        goldMedal = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\gold.png"));
        sliverMedal = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\silver.png"));
        bronzeMedal = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\bronze.png"));
        platiumMedal = ImageIO.read(new File("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\platinum.png"));
        
        bird = new Bird(200, 150, birdImage);
        pipes = new ArrayList<>();

        placePipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to be executed
                placePipes();
            }
        });
        placePipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        if(bird.isAlive()){
            drawScore(g,score,240,40);
        }
    }

    public void draw(Graphics g) {

        // Vẽ nền và nền đất
        g.drawImage(backgroundImage, 0, 0, this);
        g.drawImage(foregroundImage, 0, 0, this); // Đặt vị trí cho foreground nếu cần

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.widthPipe, pipe.heightPipe, this);
        }
        g.drawImage(foregroundImage, 0, 0, this); // Đặt vị trí cho foreground nếu cần

        bird.renderBird(g);
        
        if(!bird.isAlive()){
            drawMenuGameOver(g);
            
        }
    }

    public void drawMenuGameOver(Graphics g){
        g.drawImage(gameOverLabel, 150, 100,200,40, this);
        g.drawImage(scoreBoard, 130,170,250,150, this);
        if(score>bestScore){
            bestScore = score;
        }
        if(score >= 10){
            g.drawImage(goldMedal,MENU , MENU, this);
        }else if(score >=20){
            g.drawImage(sliverMedal, MENU, MENU, this);
        }else if(score >= 30){
            g.drawImage(bronzeMedal, MENU, MENU, this);
        }else if(score >= 40){
            g.drawImage(platiumMedal, MENU, MENU, this);
        }
        g.drawImage(restartButton, 130, 350,91,32, this);
        g.drawImage(addToLeaderBoardButton, 250, 350,130,32, this);
        
        
    }
    private boolean isTouching (Rectangle r) {
		return r.contains(clickedPoint);
    }
    
    public void drawScore(Graphics g, int score,int x, int y) {
    String scoreString = String.valueOf(score);
    for (int i = 0; i < scoreString.length(); i++) {
        String digit = String.valueOf(scoreString.charAt(i));
        BufferedImage digitImage = getDigitImage(digit);  // Hàm lấy ảnh tương ứng với từng chữ số
        g.drawImage(digitImage, x, y, this);
        x += 15; // Khoảng cách giữa các chữ số
    }
}

    private BufferedImage getDigitImage(String digit) {
        switch (digit) {
            case "0": return score0;
            case "1": return score1;
            case "2": return score2;
            case "3": return score3;
            case "4": return score4;
            case "5": return score5;
            case "6": return score6;
            case "7": return score7;
            case "8": return score8;
            case "9": return score9;
            default: return score0; 
    }
}


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    public void move() {
        if (bird.isAlive()) {
            bird.velocityY += bird.gravity;
            bird.yBird += bird.velocityY;

            if (bird.yBird >= 385) { // Kiểm tra nếu chim chạm đáy
                bird.yBird = 385;
                bird.kill();// Cố định vị trí khi chạm nền
                audio.hit();
                gameLoop.stop();
                placePipeTimer.stop();
                

            }

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.movePipe();

                if (!pipe.passed && bird.xBird > pipe.x + pipe.widthPipe && pipe.img == topPipeImage) {
                    if(!pipe.passed){
                    score += 1;
                    pipe.passed = true;
                    audio.point();
                    }
                }

                if (collision(bird, pipe)) {
                    bird.kill();
                    audio.hit();

                    if (bird.yBird >= 385) {
                        gameLoop.stop();
                        placePipeTimer.stop();
                    }
                }
            }
        }
    }

    public void placePipes() {
        int randomPipeY = (int) (0 - Pipe.heightPipe / 4 - Math.random() * (Pipe.heightPipe / 2));
        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(500, 0, topPipeImage);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(500, 0, bottomPipeImage);
        bottomPipe.y = topPipe.y + Pipe.heightPipe + openingSpace;
        pipes.add(bottomPipe);
    }

    public void resetGame() {
        bird.xBird = 200;           // Đặt lại vị trí X của con chim
        bird.yBird = 150;           // Đặt lại vị trí Y của con chim
        bird.velocityY = 0;
        bird.reHealth();
        pipes.clear();// Đặt lại vận tốc Y
        bird.gravity = 1;
        score = 0;// Đặt lại hình ảnh của chim
        repaint();             // Vẽ lại màn hình để làm mới giao diện
        gameLoop.start();
        placePipeTimer.start();
    }

    boolean collision(Bird a, Pipe b) {
        if (a.xBird > b.x && a.yBird < 0) {
            return true;
        }
        return a.xBird < b.x + b.widthPipe
                && //a's top left corner doesn't reach b's top right corner
                a.xBird + a.widthBird > b.x
                && //a's top right corner passes b's top left corner
                a.yBird < b.y + b.heightPipe
                && //a's top left corner doesn't reach b's bottom left corner
                a.yBird + a.heightBird > b.y;    //a's bottom left corner passes b's top left corner
    }
    
    private boolean isClickOnRestartButton(Point point) {
    // Vị trí và kích thước của nút restart
        int restartButtonX = 130;
        int restartButtonY = 350;
        int restartButtonWidth = 92;
        int restartButtonHeight = 31;

        return point.x >= restartButtonX && point.x <= restartButtonX + restartButtonWidth
            && point.y >= restartButtonY && point.y <= restartButtonY + restartButtonHeight;
    }
    
    private boolean isClickOnAddToLeaderBoardButton(Point point){
        int addtoleaderboardButtonX = 200;
        int addtoleaderboardButtonY = 400;
        int addtoleaderboardButtonWidth = 120;
        int addtoleaderboardButtonHeight = 40;

        return point.x >= addtoleaderboardButtonX && point.x <= addtoleaderboardButtonX + addtoleaderboardButtonWidth
            && point.y >= addtoleaderboardButtonY && point.y <= addtoleaderboardButtonY + addtoleaderboardButtonHeight;
    }
    //Key Action
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (bird.isAlive()) {
                audio.jump();
                bird.velocityY = bird.jumpStrength;  // Tạo hiệu ứng nhảy khi game chưa kết thúc
            } else {
                resetGame();  // Khởi động lại trò chơi khi game đã kết thúc
            }
        }
    }

    // Mosuse Action
    public void mouseClicked(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        clickedPoint = e.getPoint();
        if(bird.isAlive()){
            audio.jump();
            bird.velocityY = bird.jumpStrength;
        }else{
            if(isClickOnRestartButton(clickedPoint)){
                  resetGame();
            }
            if(isClickOnAddToLeaderBoardButton(clickedPoint)){
                
            }
        }
    }

}
