package GameObject;

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
        //tạo size cho panel
        setFocusable(true);
        setSize(500, 500);
        setPreferredSize(new Dimension(500, 500));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // tạo tiêu đề cho frame
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/titleText.png"));
        JLabel label = new JLabel(icon);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label, BorderLayout.EAST);

        setButtonPanel();

        add(buttonPanel);

        //xử lí sự kiện  khi click vào button start
        startButton.addActionListener(e -> {
            try {
                this.game = new Game(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // lấy frame hiện tại
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            buttonPanel.setVisible(false);
            frame.add(game);
            frame.pack();
            game.requestFocus();
        });

        //xử lí sự kiện khi click vào button leaderboard
        leaderBoardButton.addActionListener(e -> {
            this.leaderBoard = new LeaderBoard();

            // tạo frame mới để hiển thị leader board
            JFrame leaderBoardFrame = new JFrame();

            //tạo size cho frame mới khi ấn vào leaderboard button
            leaderBoardFrame.setPreferredSize(new Dimension(400, 450));
            

            leaderBoardFrame.add(leaderBoard);
            leaderBoardFrame.pack();
            leaderBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            leaderBoardFrame.setVisible(true);
        });

        setVisible(true);
    }

    public void setButtonPanel() {
        //tạo panel mới cho button panel
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridLayout(1, 2));

        //tạo button start
        this.startButton = createImageButton("/resources/playbutton.png");
        
        //thêm button vào panel
        buttonPanel.add(this.startButton);

        //tạo button leaderboard
        this.leaderBoardButton = createImageButton("/resources/leaderboardbutton.png");
        
        //thêm button vào panel
        this.buttonPanel.add(this.leaderBoardButton);
        this.buttonPanel.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        //vẽ background và foreground cho panel
        backgroundImage = new ImageIcon(getClass().getResource("/resources/background.png")).getImage();
        foregroundImage = new ImageIcon(getClass().getResource("/resources/foreground.png")).getImage();
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(foregroundImage, 0, 0, null);
    }

    private JButton createImageButton(String imagePath) {
        // tạo button không chứa viền của button
        JButton button = new JButton();
        try {
            // Tạo ImageIcon từ đường dẫn hình ảnh
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

            // Set icon cho button
            button.setIcon(icon);

            // Loại bỏ viền mặc định của nút khi click
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
