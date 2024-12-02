/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeaderBoard extends JPanel {
    private ArrayList<Player> highScore = new ArrayList<>();

    public LeaderBoard() {
        // tạo panel
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //khởi tạo leader board cho game - leader board được lưu trong file LeaderBoard.txt

        //tao label va cấu hình cho nó
        JLabel label = new JLabel("Leaderboard");
        panel.setPreferredSize(new Dimension(350, 400));
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.DARK_GRAY);
        panel.add(label);

        setHighScore();
        //cấu hình  cho panel

        panel.setOpaque(false);

        //show 10 người chơi cao nhất
        panel.add(showLeaderBoard(), BorderLayout.CENTER);
        panel.setVisible(true);

        add(panel);
    }

    public void setHighScore() {
        if(highScore.size() != 10) {
            //đường dẫn tới file LeaderBoard.txt
            String path = "D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\GameObject\\LeaderBoard.txt";
            // đọc từ  file leaderBoard.txt 10 người chơi có diểm cao nhất và lưu vào mảng highScore
            try {
                // đọc file LeaderBoard.txt
                Scanner sc = new Scanner(new File(path));

                while(sc.hasNextLine()) {
                    Player temp = new Player(sc.nextLine());
                    highScore.add(temp);
                }
                sc.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            //nếu chưa có đủ 10 người chơi thì thêm vào người  chơi mặcc định
            for (int i = highScore.size(); i < 10; i++) {
                highScore.add(new Player("Player" + (i + 1) + " 0"));
            }
        }
        
    }
    
    public void UpdateLeaderBoard(Player newHighScore) throws IOException {
        // cập nhật leader board khi có người chơi mới có điểm cao  hon nguoi chơi ở vị trí cuối cùng
        if(newHighScore.isBigger(highScore.get(9))) {
            //nếu người chơi có điểm cao hơn người chơi đầu thì thêm vào đầu tiên
            if(newHighScore.isBigger(highScore.get(0))) {
                highScore.add(0, newHighScore);
            }
            else {
                //timf vị trí ngươi chơi có điểm cao nhat trong bảng ít diểmm hơn người  chơi mới
                int i = 1;
                while (highScore.get(i).isBigger(newHighScore) && i < highScore.size()) {
                    i ++;
                }
                //thêm vào vị trí của người chơi vừa tìm thấy 
                highScore.add(i, newHighScore);
            }   
            //loại bỏ người chơi ở vị trí cuối cùng(thứ 11 do đã thêm vào 1 người chơi mới)
            if(highScore.size() > 10) highScore.remove(10);
        }
        //ghi lại vào file LeaderBoard.txt
        saveHighScoreToFile("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\GameObject\\LeaderBoard.txt");
    }

    public void saveHighScoreToFile(String path) throws IOException {
        //ghi lai vao file LeaderBoard.txt
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < 10; i++) {
            writer.write(highScore.get(i).toString());
            writer.newLine();
        }
        writer.close();
    }

    public JPanel showLeaderBoard() {
        //tạo 1 panel mới chứa leader board
        JPanel highScorePanel = new JPanel();
        highScorePanel.setLayout(new GridLayout(10, 3, 3, 10));

        //cấu hình cho panel
        highScorePanel.setSize(300, 400);
        highScorePanel.setOpaque(false);
        highScorePanel.setBorder(null);

        //hiển thị 10 người chơi có điểm cao nhất từ file LeaderBoard.txt
        for (int i = 0; i < 10; i++) {
            JLabel temp = new JLabel((i + 1) + ".");
            setLabel(temp);
            highScorePanel.add(temp);

            temp = new JLabel(highScore.get(i).getNickName());
            setLabel(temp);
            highScorePanel.add(temp);
            
            temp = new JLabel(Integer.toString(highScore.get(i).getScore()));
            setLabel(temp);
            highScorePanel.add(temp);
        }

        return highScorePanel;
    }

    public void setLabel(JLabel label) {
        // cấu  hình cho label
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.DARK_GRAY);
    }

}