package chapter10;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class StrokeChooser {

    public static BasicStroke showDialog(JButton strokeButton, String title, BasicStroke stroke) {
        final JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        // trick to create something that is final (the Array) where we can still update position 0 with
        // the chosen stroke
        final BasicStroke[] chosen = new BasicStroke[1];

        for (float w = 1.0f; w <= 11.0f; w += 2.0f) {
            final float fw = w;
            JButton btn = new JButton(""+fw);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chosen[0] = new BasicStroke(fw);
                    dialog.dispose();
                }
            });
            panel.add(btn);
        }

        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(panel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(strokeButton);
        dialog.setVisible(true);

        return chosen[0] != null ? chosen[0] : stroke;
    }

}
