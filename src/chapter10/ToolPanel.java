package chapter10;

import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel {

    static enum ShapeType { PENCIL, LINE, RECTANGLE, SQUARE, ELLIPSE, CIRCLE, TRIANGLE_EQUILATERAL, TRIANGLE_ISOCOLES, TRIANGLE_SCALENE, PENTAGON, HEXAGON};
    
    protected Color currentColor = Color.BLACK;
    protected ShapeType currentShape = ShapeType.RECTANGLE;

    public ToolPanel() {
        setupPanel();
    }

    protected void setupPanel() {
        JPanel shapePanel = new JPanel(new GridLayout(7,2));
        JButton pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = ShapeType.PENCIL;
            }
        });
    }

}
