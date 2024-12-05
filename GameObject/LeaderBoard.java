
package GameObject;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeaderBoard extends JPanel {
    private ArrayList<Player> highScore = new ArrayList<>();

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

        setHighScore();
        //cau hinh cho panel
        panel.setOpaque(false);
        //show 10 nguoi choi cao nhat
        panel.add(showLeaderBoard(), BorderLayout.CENTER);
        panel.setVisible(true);

        add(panel);
    }

    
    public void setHighScore() {
        //doc du lieu tu file LeaderBoard.txt
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\GameObject\\LeaderBoard.txt"));

            // Kiểm tra xem đối tượng được đọc từ tệp có là một arraylist ko tức là có là một Player không trước khi thêm vào highScore
            Object obj = reader.readObject();
        if (obj instanceof ArrayList<?>) {
            @SuppressWarnings("unchecked")
            ArrayList<Player> tempList = (ArrayList<Player>) obj;
            this.highScore = new ArrayList<>(tempList);
        }
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            this.highScore = new ArrayList<>();
        }
    }
    
    public void UpdateLeaderBoard(Player newHighScore) throws IOException {
        if(highScore.size() < 10) {
            highScore.add(newHighScore);
            highScore.sort((a, b) -> b.getScore() - a.getScore());
        }
        else {
            // cap nhat leader board khi co nguoi choi moi co diem cao hon nguoi choi o vi tri cuoi cung
            if(newHighScore.isBigger(highScore.get(9))) {
                //neu nguoi choi co diem cao hon nguoi dung dau thi them vao dau tien
                if(newHighScore.isBigger(highScore.get(0))) {
                    highScore.add(0, newHighScore);
                }
                else {
                    //tim vi tri nguoi choi co diem cao nhat trong bang it diem hon nguoi choi moi
                    int i = 1;
                    while (highScore.get(i).isBigger(newHighScore) && i < highScore.size()) {
                        i ++;
                    }
                    //them vao vi tri cua nguoi choi vua tim thay
                    highScore.add(i, newHighScore);
                }   
                //loai bo nguoi choi o vi tri cuoi cung(thu 11 do da them vao 1 nguoi choi moi)
                if(highScore.size() > 10) highScore.remove(10);
            }
        }   
        //ghi lai vao file LeaderBoard.txt
        saveHighScoreToFile("D:\\JAVA_2024\\JavaApplication11\\src\\javaapplication11\\GameObject\\LeaderBoard.txt");
    }

    public void saveHighScoreToFile(String path) throws IOException {
        //ghi lai vao file LeaderBoard.txt
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(path));
        writer.writeObject(highScore);
        writer.close();
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
        //neu so nguoi choi co diem cao nhat nho hon 10 thi hien thi so nguoi choi do
        if(highScore.size() < 10) {
            //hien thi so nguoi choi dang chua trong leaderboard.txt
            for(int i = 0; i < highScore.size(); i++) {
                JLabel temp = new JLabel((i + 1) + "");
                setLabel(temp);
                highScorePanel.add(temp);

                temp = new JLabel(highScore.get(i).getNickName());
                setLabel(temp);
                highScorePanel.add(temp);
                
                temp = new JLabel(Integer.toString(highScore.get(i).getScore()));
                setLabel(temp);
                highScorePanel.add(temp);
            }
            //nhung vi tr con lai trong danh sach hien thi voi ten <emptyPlayer> va diem la ...
            for (int i = highScore.size(); i < 10; i++) {
                JLabel temp = new JLabel((i + 1) + "");
                setLabel(temp);
                highScorePanel.add(temp);

                temp = new JLabel("<Unknown>");
                setLabel(temp);
                highScorePanel.add(temp);
                    
                temp = new JLabel("??");
                setLabel(temp);
                highScorePanel.add(temp);
            }
        }   
        else if(highScore.size() == 0) {
            //neu chua co nguoi choi nao thi hien thi <emptyPlayer> va diem la ...
            for(int i = 0; i < 10; i++) {
                JLabel temp = new JLabel((i + 1) + "");
                setLabel(temp);
                highScorePanel.add(temp);

                temp = new JLabel("<Unknown>");
                setLabel(temp);
                highScorePanel.add(temp);
                
                temp = new JLabel("??");
                setLabel(temp);
                highScorePanel.add(temp);
            }
        }
        else {
            //hien thi 10 nguoi choi co diem cao nhat tu file
            for (int i = 0; i < 10; i++) {
                JLabel temp = new JLabel((i + 1) + "");
                setLabel(temp);
                highScorePanel.add(temp);

                temp = new JLabel(highScore.get(i).getNickName());
                setLabel(temp);
                highScorePanel.add(temp);
                
                temp = new JLabel(Integer.toString(highScore.get(i).getScore()));
                setLabel(temp);
                highScorePanel.add(temp);
            }
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
