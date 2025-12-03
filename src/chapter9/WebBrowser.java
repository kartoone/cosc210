package chapter9;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class WebBrowser extends JFrame implements ActionListener, WindowListener {

    // Data for our browser history
    private Stack<String> backStack = new ListStack<>();
    private Stack<String> forwardStack = new ListStack<>();

    // All of our components for this instance of the Web Browser
    private JButton backButton;
    private JButton forwardButton;
    private JButton goButton;
    private JTextField urlTextField;

    // Panels that house our components
    private JPanel urlPanel; // the url text field and associated buttons
    private JWebPanel webPanel; // the main display area for the website source code
    private JPrinterPanel printerPanel; // the panel that deals with the printer and printer queue

    // For allowing multiple windows, we need to know when the last one was closed
    private static int numWindowsOpened = 0;

    public WebBrowser() {
        setLayout(new BorderLayout());

        urlPanel = new JPanel();
        backButton = new JButton("<-- Back");
        forwardButton = new JButton("Forward -->");
        goButton = new JButton("Go");
        urlTextField = new JTextField(50);

        urlPanel.add(backButton);
        urlPanel.add(forwardButton);
        urlPanel.add(urlTextField);
        urlPanel.add(goButton);
        add(urlPanel, BorderLayout.NORTH);

        webPanel = new JWebPanel();
        add(webPanel, BorderLayout.CENTER);
        
        printerPanel = new JPrinterPanel();
        add(printerPanel, BorderLayout.SOUTH);

        pack();

        // register this object to listen to all the events
        // coming from all our buttons and from the JFrame itself
        backButton.addActionListener(this);
        forwardButton.addActionListener(this);
        goButton.addActionListener(this);
        addWindowListener(this);

        // at the last possible moment, reveal the UI
        setVisible(true);
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

    protected static void createAndShowGUI() {
        WebBrowser.numWindowsOpened++;
        new WebBrowser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        WebBrowser.numWindowsOpened--;
        if (WebBrowser.numWindowsOpened == 0) {
            System.exit(0);
        }    
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    
}
