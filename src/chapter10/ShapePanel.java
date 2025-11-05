package chapter10;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;

public class ShapePanel extends JPanel implements MouseListener {

    protected ArrayList<Shape> shapes = new ArrayList<>();
    protected ToolPanel toolPanel; // reference to the tool panel object which has the currently selected shape and the currently selected color, style, etc...

    public ShapePanel(ToolPanel toolPanel) {
        this.toolPanel = toolPanel; 
        addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked shape panel");
        switch (toolPanel.currentShape) {
            case CIRCLE:
                shapes.add(new Circle(10, e.getX(), e.getY(), toolPanel.currentColor));
                break;
            case ELLIPSE:
                shapes.add(new Ellipse(20, 10, e.getX(), e.getY(), toolPanel.currentColor));
                break;
            case HEXAGON:
                shapes.add(new Hexagon(100, e.getX(), e.getY(), toolPanel.currentColor));
                break;
            case LINE:
                break;
            case PENCIL:
                break;
            case PENTAGON:
                shapes.add(new Pentagon(100, e.getX(), e.getY(), toolPanel.currentColor));
                break;
            case RECTANGLE:
                System.out.println("clicked while rectangle was selected");
                shapes.add(new Rectangle(20, 10, e.getX(), e.getY(), toolPanel.currentColor));
                break;
            case SQUARE:
                shapes.add(new Square(20, e.getX(), e.getY(), toolPanel.currentColor));
                break;
            case TRIANGLE_EQUILATERAL:
                break;
            case TRIANGLE_ISOCOLES:
                break;
            case TRIANGLE_SCALENE:
                break;
            default:
                break;
            
        }
        getParent().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
