/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StarAnimation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Santi
 */
public class BoardStar extends JPanel implements Runnable{
    private Image star;
    private Thread thread;
    private int x,y,dx=1,dy=1;
    private final int DELAY = 25;
    
    public BoardStar() {
        setBackground(Color.BLACK);
        ImageIcon ii = new ImageIcon(getClass().getResource("star.png"));
        star = ii.getImage();
        setDoubleBuffered(true);
        x = y = 10;        
        /*timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduledTask(this), 100, 25);*/
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start(); 
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    private void cycle() {
        x += dx;
            y += dy;
            if (y > getHeight()-star.getHeight(this) || y < 0) {
                dy *= -1;
            }
            if (x > getWidth()-star.getWidth(this) ||x < 0) {
                dx *= -1;
            }
    }
    
    /* DONE WITH JAVA.UTIL.TIMER
    class ScheduledTask extends TimerTask {
        ImageObserver observer;
        
        public ScheduledTask(ImageObserver observer) {
            this.observer = observer;
        }
        
        @Override
        public void run() {
            x += dx;
            y += dy;
            if (y > getHeight()-star.getHeight(observer) || y < 0) {
                dy *= -1;
            }
            if (x > getWidth()-star.getWidth(observer) ||x < 0) {
                dx *= -1;
            }
            repaint();
        } 
    }*/

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        
        while (true) {
            cycle();
            repaint();
            timeDiff = System.currentTimeMillis()-beforeTime;
            sleep = DELAY-timeDiff;
            if (sleep<=0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted\n");
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
