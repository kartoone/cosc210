package chapter10;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Graphics;

public class ShapePanel extends JPanel implements MouseListener, MouseMotionListener {

    protected ArrayList<Shape> shapes = new ArrayList<>();
    protected ToolPanel toolPanel; // reference to the tool panel object which has the currently selected shape and the currently selected color, style, etc...
    protected Integer startDragX; // coordinates of location where a drag started
    protected Integer startDragY; // these two "objects" will be null whenever we are not dragging something

    public ShapePanel(ToolPanel toolPanel) {
        this.toolPanel = toolPanel; 
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void addShape(Shape s) {
        shapes.add(s);
        getParent().repaint();
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
        startDragX = null;
        startDragY = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (startDragX==null) {
            startDragX = e.getX();
            startDragY = e.getY();

        } else {
            // we must have already been dragging!
            // delete the old line and the new line
            shapes.removeLast();
        }
        addShape(new Line(startDragX, startDragY, e.getX(), e.getY(), toolPanel.currentColor));
        System.out.println(shapes);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
