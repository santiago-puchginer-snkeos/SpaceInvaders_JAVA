/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Collision;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
    private int speed_x;
    private int speed_y;
    private int width;
    private int height;
    private boolean visible;
    private ArrayList<Missile> missiles;
    
    public Craft() {
        ImageIcon ii = new ImageIcon(getClass().getResource("craft.png"));
        im = ii.getImage();
        width = im.getWidth(null);
        height = im.getHeight(null);
        missiles = new ArrayList<>();
        visible = true;
        x = 40;
        y = 60;
        speed_x = 3;
        speed_y = 3;
    }
    
    public void move() {
        x += dx;
        y += dy;
        
        if (x<1) {
            x=1;
        }
        if (y<1) {
            y=1;
        }
    }
    
    public void fire() {
       missiles.add(new Missile(x+width, y+height/2));
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImage() { return im; }
    public ArrayList<Missile> getMissiles() { return missiles; }
    public boolean isVisible() { return visible; }
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_SPACE:
                fire();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                dx = -speed_x;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:    
                dx = speed_x;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                dy = speed_y;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                dy = -speed_y;
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
    
    public void reset() {
        visible = true;
        x = 40;
        y = 60;
        missiles.clear();
    }
}
