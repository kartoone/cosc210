package chapter10;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class ShapePanel extends JPanel {

    protected ArrayList<Shape> shapes = new ArrayList<>();

    public ShapePanel() {
        // setSize(1500,1000);
    }

    public void addShape(Shape s) {
        shapes.add(s);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
       Graphics2D g2d = (Graphics2D) g;
       for (Shape shape : shapes) {
         shape.draw(g2d);
       } 
    }
}
