package chapter9;

import java.awt.Dimension;
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
        completeButton.setEnabled(false);
        this.setPreferredSize(new Dimension(500, 100));
        printButton.addActionListener(this);
        cancelButton.addActionListener(this);
        completeButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String currentUrl = HttpManager.getCurrentURL();
        if (e.getSource() == printButton) {
            if (currentUrl.length()>0) {
                displayMessage("Starting: " + currentUrl);
                printerQueue.enqueue(currentUrl);
                completeButton.setEnabled(true);
            }
        } else if (e.getSource() == completeButton) {
            String finishedJob = printerQueue.dequeue();
            displayMessage("Finished: " + finishedJob);
            if (printerQueue.size()==0) {
                completeButton.setEnabled(false);
            }
        }
    }

    private void displayMessage(String string) {
        queueDisplay.setText(queueDisplay.getText()+"\n" + string);
    }

}
