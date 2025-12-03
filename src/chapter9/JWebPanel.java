package chapter9;

import javax.swing.*;
import java.awt.*;

public class JWebPanel extends JPanel {

    private JTextArea contentArea = new JTextArea(30, 50);

    public JWebPanel() {
        setLayout(new BorderLayout());

        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(contentArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void navigate(String url) {
        try {
            contentArea.setText(HttpManager.sendGET(url));
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
