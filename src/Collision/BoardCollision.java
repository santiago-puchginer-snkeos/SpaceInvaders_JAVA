/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Collision;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Santi
 */
public class BoardCollision extends JPanel {
    private Sound sound;
    private Timer timer;
    private Craft craft;
    private ArrayList<Alien> aliens;
    private ArrayList<Missile> missiles;
    private boolean ingame;
    private final int[][] pos = {
        {50, 29}, {40, 59}, {20, 89},
        {10, 71}, {30, 53}, {65, 48}, 
        {5, 25}, {35, 50}, {73, 21},
        {15, 49}, {96, 45}, {61, 80},
        {68, 15}, {79, 87}, {92, 12}
    }; //NUMBERS FROM 1 TO 10
    /*private final int[][] pos = { 
        {238, 29}, {250, 59}, {138, 89},
        {178, 109}, {258, 139}, {368, 239}, 
        {179, 259}, {276, 50}, {379, 150},
        {298, 209}, {256, 45}, {151, 70},
        {293, 159}, {359, 80}, {253, 60},
        {194, 59}, {299, 30}, {192, 200},
        {290, 259}, {166, 50}, {254, 90}
     };*/
    
    private final int RESET_COUNTDOWN = 7;
    
    //Reset Variables
    private DecimalFormat df;
    private long initial_time; //MS
    private long actual_time; //MS
    private double elapsed_time; //SECONDS
    private double seconds_remaining; //MS
    
    public BoardCollision() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        sound = new Sound();
        ingame = true;
        craft = new Craft();
        initAliens(false);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduledTask(), 100, 15);
        initial_time = 0;
        elapsed_time = 0;
        seconds_remaining = RESET_COUNTDOWN;
        df = new DecimalFormat("0");
    }

    private void initAliens(boolean random) {
        if (!random) {
            aliens = new ArrayList<>();
            for(int i=0;i<pos.length;i++) {
                aliens.add(new Alien((int)((pos[i][0]/100.0)*(JavaCollision.PANEL_WIDTH/2)
                    +JavaCollision.PANEL_WIDTH/2),
                    (int)((pos[i][1]/100.0)*(JavaCollision.PANEL_HEIGHT))));
            }
        }
        else {
            for(int i=0;i<pos.length;i++) {
                aliens.add(new Alien((int)Math.round(Math.random()*(JavaCollision.PANEL_WIDTH/2)
                    +JavaCollision.PANEL_WIDTH/2),
                        (int)Math.round(Math.random()*(JavaCollision.PANEL_HEIGHT-10)+5)));
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ingame) {
            Graphics2D g2d = (Graphics2D) g;
            if (craft.isVisible())
                g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),this);
            
            missiles = craft.getMissiles();
            for (int i=0; i<missiles.size(); i++) {
                Missile m = missiles.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(),this);
            }
            
            for (int i=0; i<aliens.size(); i++) {
                Alien a = aliens.get(i);
                if (a.isVisible()) {
                    g2d.drawImage(a.getImage(), a.getX(), a.getY() ,this);
                }
            }
            
            g2d.setColor(Color.WHITE);
            g2d.drawString("Aliens left: " + aliens.size(), 5, 15); 
        }
        else {
            String msg = "GAME OVER";
            String strReset = "Reset in " + String.valueOf(df.format(seconds_remaining));
            Font small = new Font("Helvetica", Font.BOLD,16);
            FontMetrics fm = this.getFontMetrics(small);
            
            g.setColor(Color.WHITE);
            g.setFont(small);
            int w_loc = (JavaCollision.PANEL_WIDTH - fm.stringWidth(msg))/2;
            int h_loc = (JavaCollision.PANEL_HEIGHT - fm.getHeight())/2;
            g.drawString(msg, w_loc, h_loc);
            g.drawString(strReset, w_loc+10, h_loc + 50);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    private void cycle() {
        
        if (aliens.isEmpty()) {
            ingame = false;
            startReset();
        }
        missiles = craft.getMissiles();
            
        for (int i=0; i<missiles.size(); i++) {
            Missile m = missiles.get(i);
            if (m.isVisible()) {
                m.move();
            }
            else {
                missiles.remove(i);
            }
        }
            
        for (int i=0; i<aliens.size(); i++) {
            Alien m = aliens.get(i);
            if (m.isVisible()) {
                m.move();
            }
            else {
                aliens.remove(i);
            }
        }
        craft.move();
        checkCollisions();
        repaint();
    }
    
    
    private void checkCollisions() {
        Rectangle rcraft = craft.getBounds();
        for (int i=0; i<aliens.size(); i++) {
            Alien a = aliens.get(i);
            Rectangle ralien = a.getBounds();
            if (rcraft.intersects(ralien)) {
                craft.setVisible(false);
                a.setVisible(false);
                ingame = false;
                startReset();
            }
        }
        
        ArrayList<Missile> misiles = craft.getMissiles();
        for (int i=0; i<misiles.size(); i++) {
            Missile m = misiles.get(i);
            Rectangle rmisile = m.getBounds();
            for (int j=0; j<aliens.size(); j++) {
                Alien a = aliens.get(j);
                Rectangle ralien = a.getBounds();
                if (rmisile.intersects(ralien)) {
                    a.setVisible(false);
                    m.setVisible(false);
                    sound.defeatedAlienSound();
                }
            }   
        }
    }
    
    class TAdapter implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                sound.shotSound();
            }
            e.consume();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                sound.shotSound();
            }
            craft.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }
        
    }
    
    class ScheduledTask extends TimerTask {

        @Override
        public void run() {
            if (ingame)
                cycle();
            else {
                //Reset Mode
                if (seconds_remaining>0.5) {
                    actual_time = System.currentTimeMillis(); // actual time in ms
                    elapsed_time = (actual_time - initial_time)/1e3; // elapsed time in ms
                    seconds_remaining = RESET_COUNTDOWN-elapsed_time;
                    repaint();
                }
                else {
                    reset();
                }
            }
        }
        
    }
    
    private void reset() {
        initial_time = 0;
        elapsed_time = 0;
        seconds_remaining = RESET_COUNTDOWN;
        craft.reset();
        aliens.clear();
        initAliens(true);
        ingame = true;
    }
    
    private void startReset() {
        initial_time = System.currentTimeMillis();
        actual_time = initial_time;
        seconds_remaining = RESET_COUNTDOWN;
        elapsed_time = 0;
    }
    
}
