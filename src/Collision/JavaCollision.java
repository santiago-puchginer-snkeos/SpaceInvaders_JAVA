/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Collision;

import javax.swing.JFrame;

/**
 *
 * @author Santi
 */
public class JavaCollision extends JFrame{
    public static final int PANEL_HEIGHT = 500;
    public static final int PANEL_WIDTH = 680;
    
    public JavaCollision() {
        add(new BoardCollision());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(PANEL_WIDTH,PANEL_HEIGHT);
        setLocationRelativeTo(null);
    }
    
    public static void main_collision() {
        new JavaCollision();
    }
}
