/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Santi
 */
public class BoardSprites extends JPanel{
    private Timer timer;
    private Image im;
    private Craft craft;
    private ArrayList<Missile> missiles;
    
    public BoardSprites() {
        setDoubleBuffered(true);
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(new TAdapter());
        craft = new Craft("craft.png",this);
        im = craft.getImage();
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduledTask(), 100, 25);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(im, craft.getX(), craft.getY(), this);
        missiles = craft.getMissiles();
        for (int i=0; i<missiles.size(); i++) {
            Missile m = missiles.get(i);
            g2.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    class TAdapter implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            e.consume();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
           craft.keyReleased(e);
        }
        
    }
    
    class ScheduledTask extends TimerTask{

        @Override
        public void run() {
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
            craft.move();
            repaint();
        }
        
    }
    
    
}
