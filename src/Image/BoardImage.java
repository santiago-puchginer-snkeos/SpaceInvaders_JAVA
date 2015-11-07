/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Image;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Santi
 */
public class BoardImage extends JPanel{
    Image im;
    
    public BoardImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("star.png"));
        im = ii.getImage();
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(im, 10, 10, null);
    }
}
