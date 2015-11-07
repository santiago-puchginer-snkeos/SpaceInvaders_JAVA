/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Collision;

import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Santi
 */
public class Sound implements LineListener{
    private final String strmissile="LaserShoot.wav";
    private final String stralien = "Explosion.wav";
    private Clip missileShot;
    private Clip alienDown;
    private AudioInputStream missile;
    private AudioInputStream alien;
    
    public Sound() {
        initSounds();
    }
    
    private void initSounds() {
        try {
            missileShot = AudioSystem.getClip();
            alienDown = AudioSystem.getClip();
            missile = AudioSystem.
                    getAudioInputStream(this.getClass().
                    getResourceAsStream(strmissile));
            alien = AudioSystem.
                    getAudioInputStream(this.getClass().
                    getResourceAsStream(stralien));
            missileShot.open(missile);
            alienDown.open(alien);
            missileShot.addLineListener(this);
            alienDown.addLineListener(this);
            
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void shotSound() {
        if (missileShot.isActive()) {
            missileShot.stop();
        }
        missileShot.setFramePosition(0);
        missileShot.start();
    }
    
    public void defeatedAlienSound() {
        if (alienDown.isActive()) {
            alienDown.stop();
        }
        alienDown.setFramePosition(0);
        alienDown.start();
    }

    @Override
    public void update(LineEvent event) {
        Type t = event.getType();
        if (t == Type.CLOSE) {
            initSounds();
        }
    }
    
}
