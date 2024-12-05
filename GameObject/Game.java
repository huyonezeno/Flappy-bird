package GameObject;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Game extends JPanel implements ActionListener, KeyListener {
    // kích thước của JPanel
    private int boardWidth = 500;
    private int boardHeight = 500;

    // các ảnh đồ họa cần thiết trong game
    private BufferedImage birdImage[] = new BufferedImage[3];
    private BufferedImage bottomPipeImage;
    private BufferedImage topPipeImage;
    private BufferedImage foregroundImage;
    private BufferedImage backgroundImage;
    private BufferedImage [] scoreNums = new BufferedImage[10];

    private String pathToResouce = "D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\resources\\";

    // vòng lặp thời gian của game
    private Timer gameLoop;
    private Timer placePipeTimer;

    // thống kế điểm
    private int score = 0;
    private int bestScore = 0;
   
    // khai báo âm thanh trong game
    private static Audio audio = new Audio();

    //Bird
    private Bird bird;
    
    // khai báo màn hình game khi trò chơi kết thúc
    private GameOverScreen gameOverScreen;

    //Pipe
    private ArrayList<Pipe> pipes;
    

    public Game(int BestScore) throws IOException {
        setFocusable(true);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        addKeyListener(this);

        // lưu điểm tốt nhất từ màn trước đó khi thwucj hiện chơi lại
        this.bestScore = BestScore;

        // Load ảnh
        birdImage[0] = ImageIO.read(new File(pathToResouce + "yellowBird1.png"));
        birdImage[1] = ImageIO.read(new File(pathToResouce + "yellowBird2.png"));
        birdImage[2] = ImageIO.read(new File(pathToResouce + "yellowBird3.png"));
        topPipeImage = ImageIO.read(new File(pathToResouce + "pipe-south.png"));
        bottomPipeImage = ImageIO.read(new File(pathToResouce + "pipe-north.png"));
        foregroundImage = ImageIO.read(new File(pathToResouce + "foreground.png"));
        backgroundImage = ImageIO.read(new File(pathToResouce + "background.png"));
        for(int i = 0; i < 10; i++) {
            scoreNums[i] = ImageIO.read(new File(pathToResouce + i + ".png"));
        }
        
        bird = new Bird(200, 150, birdImage);
        pipes = new ArrayList<>();

        // sau 1,5 giây sẽ tạo 1 cặp ống mới
        placePipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to be executed
                placePipes();
            }
        });
        placePipeTimer.start();

        // Tạo vòng lặp thời gian cho game ở mứcc 60 khung hình trên giây
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        if(bird.isAlive()){ // vẽ điểm trong thời gian thực nếu con chim còn sống
            drawScore(g,score,240,40);
        } else {
            if(this.score > this.bestScore) {
                this.bestScore = this.score;
            }
            try {
                this.gameOverScreen = new GameOverScreen(this.score, this.bestScore);
            } catch (IOException ex) {
            }
            this.gameOverScreen.setVisible(true);
            revalidate();// thực hiện bố trị lại các thành phần trong panel
            repaint();// vẽ lại giao diện người dùng
            add(gameOverScreen);
        }
    }

    public void draw(Graphics g) {

        // Vẽ nền và nền đất
        g.drawImage(backgroundImage, 0, 0, this);
        g.drawImage(foregroundImage, 0, 0, this); // Đặt vị trí cho foreground nếu cần

        //vẽ các ống
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.widthPipe, pipe.heightPipe, this);
        }
        g.drawImage(foregroundImage, 0, 0, this); // Đặt vị trí cho foreground nếu cần

        // vẽ Bird
        bird.renderBird(g);
    }
    
    // vẽ điểm số với các ảnh chữ số cho trước
    public void drawScore(Graphics g, int score,int x, int y) {
    String scoreString = String.valueOf(score);
    for (int i = 0; i < scoreString.length(); i++) {
        String digit = String.valueOf(scoreString.charAt(i));
        BufferedImage digitImage = getDigitImage(digit);  // Hàm lấy ảnh tương ứng với từng chữ số
        g.drawImage(digitImage, x, y, this);
        x += 15; // Khoảng cách giữa các chữ số
    }
}
    //phân tích điểm để lấy ảnh tương ứng
    private BufferedImage getDigitImage(String digit) {
        return scoreNums[Integer.parseInt(digit)];
}

    // phương thức được gọi sau mỗi 1 chu kì 1000/60 giây để tạo chuyển động mượt mà
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    // phương thức để di chuyển đối tượng Bird và các cột trong game
    public void move() {
        if (bird.isAlive()) {
            bird.velocityY += bird.gravity;
            bird.yBird += bird.velocityY;
            bird.yBird = Math.min(bird.yBird, 385); // Giới hạn vị trí của chim không cho vượt
            if (bird.yBird == 385) { // Kiểm tra nếu chim chạm đáy
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

    // thiết lập vị trí của ống
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

    //reset game khi game kết thúc
    public void resetGame() {
        bird.xBird = 200;           // Đặt lại vị trí X của con chim
        bird.yBird = 150;           // Đặt lại vị trí Y của con chim
        bird.velocityY = 0;         // Đặt lại vận tốc Y
        bird.reHealth();            // Đặt lại trạng thái sống của con chim
        pipes.clear();              // Xóa các ống 
        score = 0;                  // Đặt lại điểm của chim
        repaint();                  // Vẽ lại màn hình để làm mới giao diện
        gameLoop.start();           // Bắt đầu lại vòng lặp game
        placePipeTimer.start();     // Bắt đầu lại vòng lặp tạo ống

    }

    // điều kiện va chạm
    boolean collision(Bird a, Pipe b) {
        return a.xBird < b.x + b.widthPipe
                && //a's top left corner doesn't reach b's top right corner
                a.xBird + a.widthBird > b.x
                && //a's top right corner passes b's top left corner
                a.yBird < b.y + b.heightPipe
                && //a's top left corner doesn't reach b's bottom left corner
                a.yBird + a.heightBird > b.y;    //a's bottom left corner passes b's top left corner
    }
    

    //hành động cho Bird bay lên khi nhấn phím space
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (bird.isAlive()) {
                audio.jump();
                bird.velocityY = bird.jumpStrength;  // Tạo hiệu ứng nhảy khi game chưa kết thúc
            }
        }
    }
}
