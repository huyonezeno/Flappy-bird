package GameObject;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable { // implements Serializable để có thể lưu trữ đối tượng vào file một cách tuần tự
    private static final long serialVersionUID = 1L;
    private int score;
    private String nickName;
    public Player() {}
    public Player(String info) {
        String [] in = info.split(" ");
        this.nickName = in[0];
        this.score = Integer.parseInt(in[1]);
    }

    public String getNickName() {
        return nickName;
    }

    public int getScore() {
        return score;
    }

    public boolean isBigger(Player p2) {
        return score > p2.score;
    }

    @Override
    public String toString() {
        return nickName + " " + score;
    }
}
