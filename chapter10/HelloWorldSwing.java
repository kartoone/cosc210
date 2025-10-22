package chapter10;

/*
 * HelloWorldSwing.java requires no other files. 
 */
import java.awt.GridLayout;
import javax.swing.*;        
 
public class HelloWorldSwing {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel label = new JLabel("Name:");
        panel.add(label);
 
        JTextField nameField = new JTextField(8);
        panel.add(nameField);

        JLabel label2 = new JLabel("Occupation:");
        panel.add(label2);
 
        JTextField occupationField = new JTextField(8);
        panel.add(occupationField);

        JLabel blank = new JLabel("");
        panel.add(blank);

        JButton button = new JButton ("Click me");
        panel.add(button);

        frame.getContentPane().add(panel);

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
