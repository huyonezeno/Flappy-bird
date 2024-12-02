/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication11;

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
    private static ArrayList<Player> highScore = new ArrayList<>();

    public LeaderBoard() {
        // tao panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //khoi tao leader board cho game - leader board duoc luu trong file LeaderBoard.txt
        //tao label va cau hinh cho no
        JLabel label = new JLabel("Leaderboard");
        panel.setPreferredSize(new Dimension(350, 400));
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.DARK_GRAY);
        panel.add(label);

        // doc file LeaderBoard.txt
        setHighScore("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\LeaderBoard.txt");
        panel.setOpaque(false);
        //show 10 nguoi choi cao nhat
        panel.add(showLeaderBoard(), BorderLayout.CENTER);
        panel.setVisible(true);

        add(panel);
    }

    public void setHighScore(String path) {
        // doc tu file leaderBoard.txt 10 nguoi choi co diem cao nhat va luu vao mang highScore
        try {
            // doc file LeaderBoard.txt
            Scanner sc = new Scanner(new File(path));

            while(sc.hasNextLine()) {
                Player temp = new Player(sc.nextLine());
                highScore.add(temp);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void UpdateLeaderBoard(Player newHighScore) throws IOException {
        // cap nhat leader board khi co nguoi choi moi co diem cao hon nguoi choi o vi tri cuoi cung
        if(newHighScore.isBigger(highScore.get(9))) {
            //neu nguoi choi co diem cao hon nguoi dung dau thi them vao dau tien
            if(newHighScore.isBigger(highScore.get(0))) {
                highScore.add(0, newHighScore);
            }
            else {
                //tim vi tri nguoi choi co diem cao nhat trong bang it diem hon nguoi choi moi
                int i = 1;
                while (highScore.get(i).isBigger(newHighScore) && i < 10) {
                    i ++;
                }
                //them vao vi tri cua nguoi choi vua tim thay
                highScore.add(i, newHighScore);
            }   
            //loai bo nguoi choi o vi tri cuoi cung(thu 11 do da them vao 1 nguoi choi moi)
            highScore.remove(10);
        }
        //ghi lai vao file LeaderBoard.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\LeaderBoard.txt"))) {
            for(Player p : highScore) {
                bw.write(p.toString());
                bw.newLine();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public JPanel showLeaderBoard() {
        // hien thi 10 nguoi choi co diem cao nhat
        //tao 1 panel moi chua leader board
        JPanel highScorePanel = new JPanel();
        highScorePanel.setLayout(new GridLayout(10, 3, 3, 10));
        //cau hinh cho panel
        highScorePanel.setSize(300, 400);
        highScorePanel.setOpaque(false);
        highScorePanel.setBorder(null);
        //hien thi 10 nguoi choi co diem cao nhat tu file
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
        // cau hinh cho label
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.DARK_GRAY);
    }
    //kiem tra xem nguoi choi co phai la nguoi choi co diem cao trong top 10 hay khong
    public boolean isHighScore(Player newHighScore) {
        
        return newHighScore.isBigger(highScore.get(9));
    }

}