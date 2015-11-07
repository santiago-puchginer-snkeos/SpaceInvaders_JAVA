/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import javax.swing.JFrame;

/**
 *
 * @author Santi
 */
public class JavaSprites extends JFrame{
    public JavaSprites() {
        add(new BoardSprites());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480,320);
        setLocationRelativeTo(null);
        setTitle("R-Type");
        setVisible(true);
        setResizable(false);
    }
    
    public static void main_sprites() {
        new JavaSprites();
    }
}
