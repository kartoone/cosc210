package chapter10;

import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToolPanel extends JPanel {

    static enum ShapeType { PENCIL, LINE, RECTANGLE, SQUARE, ELLIPSE, CIRCLE, TRIANGLE_EQUILATERAL, TRIANGLE_ISOCOLES, TRIANGLE_SCALENE, PENTAGON, HEXAGON};
    
    protected Color currentColor = Color.RED;
    protected Color fillColor = null; // null means transparent
    protected BasicStroke stroke = new BasicStroke(1.0f);
    protected ShapeType currentShape = ShapeType.RECTANGLE;
    protected ArrayList<ToolButton> buttons = new ArrayList<>();

    public ToolPanel() {
        setupPanel();
    }

    protected void deselectButtons() {
        for (ToolButton toolButton : buttons) {
            toolButton.isSelected = false;
            toolButton.repaint();
        }
    }

    protected void setupPanel() {
        this.setLayout(new GridLayout(18,1));
        ToolButton pencilButton = new ToolButton(this, ShapeType.PENCIL, "Pencil");
        ToolButton lineButton = new ToolButton(this, ShapeType.LINE, "Line");
        ToolButton rectangleButton = new ToolButton(this, ShapeType.RECTANGLE, "Rectangle");
        ToolButton squareButton = new ToolButton(this, ShapeType.SQUARE, "Square");
        ToolButton ellipseButton = new ToolButton(this, ShapeType.ELLIPSE, "Ellipse");
        ToolButton circleButton = new ToolButton(this, ShapeType.CIRCLE, "Circle");
        ToolButton triEquiButton = new ToolButton(this, ShapeType.TRIANGLE_EQUILATERAL, "Equilateral");
        ToolButton triIsocButton = new ToolButton(this, ShapeType.TRIANGLE_ISOCOLES, "Isocoles");
        ToolButton triScaleButton = new ToolButton(this, ShapeType.TRIANGLE_SCALENE, "Scalene");
        ToolButton pentagonButton = new ToolButton(this, ShapeType.PENTAGON, "Pentagon");
        ToolButton hexagonButton = new ToolButton(this, ShapeType.HEXAGON, "Hexagon");
        rectangleButton.isSelected = true;
        add(pencilButton);
        add(lineButton);
        add(rectangleButton);
        add(squareButton);
        add(ellipseButton);
        add(circleButton);
        add(triEquiButton);
        add(triIsocButton);
        add(triScaleButton);
        add(pentagonButton);
        add(hexagonButton);    
        buttons.add(pencilButton);
        buttons.add(lineButton);
        buttons.add(rectangleButton);
        buttons.add(squareButton);
        buttons.add(ellipseButton);
        buttons.add(circleButton);
        buttons.add(triEquiButton);
        buttons.add(triIsocButton);
        buttons.add(triScaleButton);
        buttons.add(pentagonButton);
        buttons.add(hexagonButton);

        JButton colorButton = new JButton(""+currentColor);
        add(colorButton);
        colorButton.setBackground(currentColor);
        colorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(colorButton, "Choose a color", currentColor);
                if (newColor != null) {
                    currentColor = newColor;
                    colorButton.setBackground(currentColor);
                }

            }
            
        });

        JButton fillButton = new JButton(""+(fillColor!=null?fillColor:"transparent"));
        add(fillButton);
        fillButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(fillButton, "Choose a fill color", fillColor);
                if (newColor != null) {
                    fillColor = newColor;
                    fillButton.setText(""+(fillColor!=null?fillColor:"transparent"));
                    fillButton.setBackground(fillColor);
                }

            }
            
        });

        JButton strokeButton = new JButton(""+stroke);
        add(strokeButton);
        strokeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BasicStroke newStroke = StrokeChooser.showDialog(strokeButton, "Choose a line width", stroke);
                if (newStroke != null) { // check for null in case the user clicked cancel
                    stroke = newStroke;
                    strokeButton.setText(""+stroke);
                }

            }
            
        });
    
    }

}
