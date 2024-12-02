package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameOverScreen extends JPanel {
    private int score;
    private int bestScore;
    Image foregroundImage;
    Image backgroundImage;
    Image gameover;
    Image scoreboardImage;
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
    private LeaderBoard leaderBoard;
    private TextField nameText;
    private JPanel addLeaderBoardPanel;
    private JFrame addLeaderBoardFrame;

    // Medal images
    Image gold;
    Image silver;
    Image bronze;
    Image platinum;
    
    public GameOverScreen(int score, int bestScore) throws IOException {
        this.score = score;
        this.bestScore = bestScore;
        setPreferredSize(new Dimension(500, 500)); // kích thước khung chung
        initComponents();
        setOpaque(false); // Để bo qua hiển thị ảnh nền
    }

    private void initComponents() throws IOException {
        setLayout(null);

        // Tải ảnh bảng điểm
        scoreboardImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/scoreCard.png")).getImage();
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
        
        // Tải ảnh huân chương//
        gold = new ImageIcon(getClass().getResource("/javaapplication11/resources/gold.png")).getImage();
        silver = new ImageIcon(getClass().getResource("/javaapplication11/resources/silver.png")).getImage();
        bronze = new ImageIcon(getClass().getResource("/javaapplication11/resources/bronze.png")).getImage();
        platinum = new ImageIcon(getClass().getResource("/javaapplication11/resources/platinum.png")).getImage();
       
        /*Nút chơi lại*/
        ImageIcon restartIcon = new ImageIcon(getClass().getResource("/javaapplication11/resources/playbutton.png"));
        Image restartImg = restartIcon.getImage().getScaledInstance(91, 45, Image.SCALE_SMOOTH);
        JButton restartButton = new JButton(new ImageIcon(restartImg));
        restartButton.setBounds(110, 290, 91, 45);
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);

        // Thêm ActionListener để xử lý sự kiện click
        restartButton.addActionListener(e -> {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(restartButton); // Lấy frame hiện tại
            if (currentFrame != null) {
                currentFrame.dispose(); // Đóng frame hiện tại
            }
            // Tạo và hiển thị StartScreen mới
            JFrame newFrame = new JFrame("Flappy Bird");
            try {
                Game startScreen = new Game(); // Khởi tạo màn hình mới
                newFrame.add(startScreen);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            newFrame.pack();
            newFrame.setLocationRelativeTo(null);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.setVisible(true);
        });   
        // Thêm nút vào giao diện
        add(restartButton);

        
        /*Nút xem bảng điểm*/
        ImageIcon leaderboardIcon = new ImageIcon(getClass().getResource("/javaapplication11/resources/leaderboardbutton.png"));
        Image leaderboardImg = leaderboardIcon.getImage().getScaledInstance(91,45, Image.SCALE_SMOOTH);
        JButton leaderboardButton = new JButton(new ImageIcon(leaderboardImg));
        leaderboardButton.setBounds(290, 290, 91,45 );
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.setBorderPainted(false);

        // Thêm ActionListener để xử lý sự kiện click
        leaderboardButton.addActionListener(e -> {
            this.leaderBoard = new LeaderBoard();
            // lay frame hien tai
            JFrame leaderBoardFrame = new JFrame();
            //tao size
            leaderBoardFrame.setPreferredSize(new Dimension(400, 450));
            //add leaderBoard vao frame va cai dat 1 so thuoc tinh
            leaderBoardFrame.add(leaderBoard);
            leaderBoardFrame.pack();
            leaderBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            leaderBoardFrame.setVisible(true);
        });

        // Thêm nút vào giao diện
        add(leaderboardButton);
        
        
        /*Nút thêm vào bảng xếp hạng*/
        ImageIcon addtoleaderboardIcon = new ImageIcon(getClass().getResource("/javaapplication11/resources/addtoleaderboard.png"));
        Image addtoleaderboardImg = addtoleaderboardIcon.getImage().getScaledInstance(130, 32, Image.SCALE_SMOOTH);
        JButton addtoleaderboardButton = new JButton(new ImageIcon(addtoleaderboardImg));
        addtoleaderboardButton.setBounds(180, 340, 130, 32);
        addtoleaderboardButton.setContentAreaFilled(false);
        addtoleaderboardButton.setBorderPainted(false);

        // Thêm ActionListener để xử lý sự kiện click
        addtoleaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo khung JFrame cho việc thêm tên vào Leaderboard
                addLeaderBoardFrame = new JFrame("Add to Leaderboard");
                addLeaderBoardPanel = new JPanel();
                addLeaderBoardPanel.setLayout(new BoxLayout(addLeaderBoardPanel, BoxLayout.Y_AXIS));
                addLeaderBoardPanel.setPreferredSize(new Dimension(300, 200));
                addLeaderBoardPanel.setBackground(new Color(0, 0, 0, 150));

                JLabel promptLabel = new JLabel("Enter your name:");
                promptLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                promptLabel.setForeground(Color.WHITE);
                promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                addLeaderBoardPanel.add(promptLabel);

                addLeaderBoardPanel.add(Box.createVerticalStrut(10));

                nameText = new TextField();
                nameText.setMaximumSize(new Dimension(200, 30));
                addLeaderBoardPanel.add(nameText);

                addLeaderBoardPanel.add(Box.createVerticalStrut(20));

                JButton submitButton = new JButton("Submit");
                submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                addLeaderBoardPanel.add(submitButton);

                addLeaderBoardFrame.add(addLeaderBoardPanel);
                addLeaderBoardFrame.pack();
                addLeaderBoardFrame.setLocationRelativeTo(null);
                addLeaderBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addLeaderBoardFrame.setVisible(true);

                // Thiết lập ActionListener cho nút Submit
                
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameText.getText().trim();
                        if (name.length() > 0) {
                            try {
                                // Giả sử Player có constructor Player(String name, int score)
                                leaderBoard.UpdateLeaderBoard(new Player(name + " " + score));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            // Xóa tất cả các thành phần hiện tại trong khung Add Leaderboard
                            addLeaderBoardFrame.getContentPane().removeAll();

                            // Thêm JLabel thông báo thành công
                            JLabel successLabel = new JLabel("Your name has been added to the leaderboard!");
                            successLabel.setFont(new Font("Arial", Font.BOLD, 16));
                            successLabel.setForeground(Color.GREEN);
                            successLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            addLeaderBoardFrame.getContentPane().setLayout(new BorderLayout());
                            addLeaderBoardFrame.getContentPane().add(successLabel, BorderLayout.CENTER);

                            // Làm mới giao diện
                            addLeaderBoardFrame.revalidate();
                            addLeaderBoardFrame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(addLeaderBoardFrame, "Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });

        // Thêm nút vào giao diện
        add(addtoleaderboardButton);
           
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ nền (như các class trên chỉ thêm mỗi gameOver và scoreboardImage)
        gameover = new ImageIcon(getClass().getResource("/javaapplication11/resources/gameOverText.png")).getImage();

        // Vẽ chữ "Game Over
        g.drawImage(gameover, 150, 60, 200, 40, null);

        // Vẽ bảng điểm căn giữa và tăng kích thước
        g.drawImage(scoreboardImage, 100,130,294,148, null);

        // Chọn ảnh huân chương dựa trên điểm số
        Image medalImage = null;
        if(score >= 40){
            medalImage = platinum;
        }else if (score >= 30) {
            medalImage = gold;
        } else if (score >= 20) {
            medalImage = silver;
        } else if (score >= 10) {
            medalImage = bronze;
        }

        // Vẽ ảnh huân chương lên trên bảng điểm
        if (medalImage != null) {
            g.drawImage(medalImage, 135,185 , 57, 57, null);
        }
        
        //vẽ score và bestscore
        drawScore(g, score, 330, 175);
        drawScore(g, bestScore, 330, 230);
        
    }
    
    public void drawScore(Graphics g, int score,int x, int y) {
    String scoreString = String.valueOf(score);
    for (int i = 0; i < scoreString.length(); i++) {
        String digit = String.valueOf(scoreString.charAt(i));
        BufferedImage digitImage = getDigitImage(digit);  // Hàm lấy ảnh tương ứng với từng chữ số
        g.drawImage(digitImage, x, y, 12, 18, this);
        x += 10; // Khoảng cách giữa các chữ số
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
    public void setTextField() {
        // Thêm textField vào frame moi
            JFrame addLeaderBoardFrame = new JFrame();

            addLeaderBoardFrame.setPreferredSize(new Dimension(400, 200));
            this.addLeaderBoardPanel = new JPanel();
            this.addLeaderBoardPanel.setPreferredSize(new Dimension(300, 200));

            this.nameText = new TextField();
            this.addLeaderBoardPanel.setLayout(new GridLayout(2, 2));
            this.addLeaderBoardPanel.setSize(300, 200);
            this.addLeaderBoardPanel.add(new JLabel("Enter your name:"));
            this.addLeaderBoardPanel.add(this.nameText);
            //them nut submit
            JButton submitButton = new JButton("Submit");
            this.addLeaderBoardPanel.add(submitButton);

            addLeaderBoardFrame.add(this.addLeaderBoardPanel);
        //khi nhan nut submit thi them vao leader board dong thoi mo ra 1 label thong bao
            submitButton.addActionListener(e -> {
                String name = nameText.getText();
                if (name.length() > 0) {
                    try {
                        this.leaderBoard.UpdateLeaderBoard(new Player(name + " " + this.score));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                this.addLeaderBoardPanel.add(new JLabel("Your name has been added to the leaderboard!"));
                
            });

        this.addLeaderBoardPanel.setOpaque(false);
        addLeaderBoardFrame.pack();
        addLeaderBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addLeaderBoardFrame.setVisible(true);
    }

}