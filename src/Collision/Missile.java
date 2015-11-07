/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Collision;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Santi
 */
public class Missile {
    private Image im;
    private int x;
    private int y;
    private int width, height;
    private boolean visible;
    
    private final int SPEED = 3;
    
    public Missile(int x, int y) {
        ImageIcon ii = new ImageIcon(getClass().getResource("missile.png"));
        im = ii.getImage();
        visible = true;
        width = im.getWidth(null);
        height = im.getHeight(null);
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImage() { return im; }
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
    
    public void move() {
        x += SPEED;
        if (x>JavaCollision.PANEL_WIDTH) {
            visible = false;
        } else {
        }
    }
    
}
