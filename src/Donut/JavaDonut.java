/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Donut;

import Donut.BoardDonut;
import javax.swing.JFrame;

/**
 *
 * @author Santi
 */
public class JavaDonut extends JFrame{
    public JavaDonut() {
        add(new BoardDonut());
        setTitle("Donut");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 310);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main_donut() {
        JavaDonut s = new JavaDonut();
    }
}
