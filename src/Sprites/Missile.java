/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Santi
 */
public class Missile {
    private Image im;
    private int x;
    private int y;
    private int panel_width;
    private boolean visible;
    private final static int SPEED = 3;
    
    public Missile(int x, int y, int panel_width) {
        ImageIcon ii = new ImageIcon(getClass().getResource("missile.png"));
        im = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        this.panel_width = panel_width;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImage() { return im; }
    public boolean isVisible() { return visible; }
    
    public void move() {
        x += SPEED;
        if (x > panel_width) {
            visible = false;
        }
    }
    
}
