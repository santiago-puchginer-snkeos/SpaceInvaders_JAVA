/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StarAnimation;

import javax.swing.JFrame;

/**
 *
 * @author Santi
 */
public class JavaStar extends JFrame{
    public JavaStar() {
        add(new BoardStar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Star Animation");
        setLocationRelativeTo(null);
        setSize(600,480);
        setResizable(true);
        setVisible(true);
    }
    
    public static void main_star() {
        new JavaStar();
    }
}
