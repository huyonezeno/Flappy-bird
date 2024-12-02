package javaapplication11;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class StartScreen extends JPanel {
    //khai bao anh nen
    private Image foregroundImage;
    private Image backgroundImage;
    //khai bao panel chua button
    private JPanel buttonPanel;
    //khai bao cac button
    private JButton startButton;
    private JButton leaderBoardButton;
    //khai bao leaderBoard
    private LeaderBoard leaderBoard;
    private Game game;


    public StartScreen() {
        //tao size cho panel
        setFocusable(true);
        setSize(500, 500);
        setPreferredSize(new Dimension(500, 500));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // tao icon cho frame
        ImageIcon icon = new ImageIcon(getClass().getResource("/javaapplication11/resources/titleText.png"));
        JLabel label = new JLabel(icon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label, BorderLayout.EAST);

        setButtonPanel();

        add(buttonPanel);

        //thuc hien action khi click vao button start
        startButton.addActionListener(e -> {
            try {
                this.game = new Game();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // lay frame hien tai
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            buttonPanel.setVisible(false);
            frame.add(game);
            frame.pack();
            game.requestFocus();
        });

        //thuc hien action khi click vao button leaderboard
        leaderBoardButton.addActionListener(e -> {
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

        setVisible(true);
    }

    public void setButtonPanel() {
        //tao panel moi cho button panel
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridLayout(1, 2));

        //tao button start
        this.startButton = createImageButton("/javaapplication11/resources/playbutton.png");
        
        //add button vao panel
        buttonPanel.add(this.startButton);

        //tao button leaderboard
        this.leaderBoardButton = createImageButton("/javaapplication11/resources/leaderboardbutton.png");
        
        //add button vao panel
        this.buttonPanel.add(this.leaderBoardButton);
        this.buttonPanel.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        //tao background va foreground cho panel
        backgroundImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/background.png")).getImage();
        foregroundImage = new ImageIcon(getClass().getResource("/javaapplication11/resources/foreground.png")).getImage();
        //ve background va foreground
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(foregroundImage, 0, 0, null);
    }

    private JButton createImageButton(String imagePath) {
        // tao button voi icon khong chua vien mac dinh cua nut
        JButton button = new JButton();
        try {
            // Tạo ImageIcon từ đường dẫn hình ảnh
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            // Set icon cho button
            button.setIcon(icon);
            // Loại bỏ viền mac dinh nút
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return button;
    }
}
