/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

    private String soundHit = "/sound/hit.wav";
    private String soundJump = "/sound/jump.wav";
    private String soundPoint = "/sound/point.wav";

    private void playSound(String sound) {

        // tải và chạy âm thanh 
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(sound));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Count not load %s.wav!\n", sound);
        }
    }

    // âm thanh khi nhảy
    public void jump() {
        playSound(soundJump);
    }

    // âm thanh khi vượt qua được chướng ngại vật
    public void point() {
        playSound(soundPoint);
    }

    // âm thanh khi chạm đất
    public void hit() {
        playSound(soundHit);
    }
}
