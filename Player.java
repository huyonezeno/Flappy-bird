package javaapplication11;

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

    public int compareTo(Player p) {
        return -Integer.compare(p.score, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return score == player.score && nickName.equals(player.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName, score);
    }
}
