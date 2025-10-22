package chapter10;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
/*
 * HelloWorldSwing.java requires no other files. 
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;        
 
public class HelloWorldSwing {

    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String> occupations = new ArrayList<>();

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2));

        JLabel label = new JLabel("Name:");
        topPanel.add(label);
 
        JTextField nameField = new JTextField(8);
        topPanel.add(nameField);

        JLabel label2 = new JLabel("Occupation:");
        topPanel.add(label2);
 
        JTextField occupationField = new JTextField(8);
        topPanel.add(occupationField);

        JLabel blank = new JLabel("");
        topPanel.add(blank);

        JButton button = new JButton ("Submit");
        topPanel.add(button);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JTextArea list = new JTextArea();
        list.setEnabled(false);
        mainPanel.add(list, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                names.add(name);
                occupations.add(name);
                list.setText(name + ", " + occupation + "\n" + list.getText());
                System.out.println("You clicked the button: " + name + ", " + occupation);
            }
        });

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
