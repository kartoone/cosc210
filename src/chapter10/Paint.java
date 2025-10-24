package chapter10;

import javax.swing.*;
import java.awt.BorderLayout;

public class Paint {

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    protected static void createAndShowGUI() {
        JFrame frame = new JFrame("Paint 3000");
        frame.setLayout(new BorderLayout());
    }

}
