package chapter9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JPrinterPanel extends JPanel implements ActionListener {

    private Queue<String> printerQueue = new ListQueue<>();
    private JTextArea queueDisplay = new JTextArea(5,50);
    private JButton printButton = new JButton("Print");
    private JButton cancelButton = new JButton("Cancel");
    private JButton completeButton = new JButton("Complete");
    
    public JPrinterPanel() {
        this.add(queueDisplay);
        this.add(printButton);
        this.add(cancelButton);
        this.add(completeButton);
        printButton.addActionListener(this);
        cancelButton.addActionListener(this);
        completeButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
