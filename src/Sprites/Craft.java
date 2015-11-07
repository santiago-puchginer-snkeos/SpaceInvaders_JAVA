/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Santi
 */
public class Craft {

    private Image im;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int PANEL_WIDTH = 480;
    private ArrayList<Missile> missiles;
    private int craft_size;
    
    public Craft(String s, ImageObserver obs) {
        ImageIcon ii = new ImageIcon(getClass().getResource(s));
        im = ii.getImage();
        craft_size = im.getWidth(obs);
        missiles = new ArrayList<>();
        x = 40;
        y = 60;
    }
    
    public void move() {
        x += dx;
        y += dy;
    }
    
    public void fire() {
       missiles.add(new Missile(x+craft_size, y+craft_size/2, PANEL_WIDTH));
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImage() { return im; }
    public ArrayList<Missile> getMissiles() { return missiles; }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_SPACE:
                fire();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                dx = -1;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:    
                dx = 1;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                dy = 1;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                dy = -1;
                break;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                dx = 0;
            break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:    
                dx = 0;
            break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                dy = 0;
            break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                dy = 0;   
            break;
        }
    }
}
