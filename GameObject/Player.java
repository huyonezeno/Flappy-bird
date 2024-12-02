package GameObject;

import java.util.Objects;

public class Player {
    private int score;
    private String nickName;
    public Player() {}
    public Player(String info) {
        String [] in = info.split(" ");
        this.nickName = in[0];
        this.score = Integer.parseInt(in[1]);
    }
    
    // tạo người chơi với tên và điểm
    public String getNickName() {
        return nickName;
    }
    // lấy điểm của người được chỉ định
    public int getScore() {
        return score;
    }

    // so sánh điểm giữa 2 người chơi
    public boolean isBigger(Player p2) {
        return score > p2.score;
    }

}
