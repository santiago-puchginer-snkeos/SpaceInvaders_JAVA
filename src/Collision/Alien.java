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
public class Alien {
    private int x,y;
    private int dx,dy;
    private Image im;
    private int height,width;
    private boolean visible;
    
    public Alien(int x, int y) {
        ImageIcon ii = new ImageIcon(getClass().getResource("alien.png"));
        im = ii.getImage();
        this.x = x;
        this.y = y;
        visible = true;
        width = im.getWidth(null);
        height = im.getHeight(null);   
        dx = (int)Math.round(Math.random()+3);  //Default: 1
        dy = (int)Math.round(Math.random()*3);
    }
    
    public void move() {
        if (x<0) {
            x=JavaCollision.PANEL_WIDTH;
        } 
        x -= dx;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isVisible() { return visible; }
    public Image getImage() { return im; }
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
