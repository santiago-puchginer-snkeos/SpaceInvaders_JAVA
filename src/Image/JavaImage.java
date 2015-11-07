/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Image;

import javax.swing.JFrame;
/**
 *
 * @author Santi
 */
public class JavaImage extends JFrame{
    public JavaImage() {
        add(new BoardImage());
        setTitle("Image");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main_image() {
        JavaImage j = new JavaImage();
    }
}
