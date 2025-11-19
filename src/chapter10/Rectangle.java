package chapter10;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Shape {

    /**
     * Specialized constructor to initialize the length and width
     * 
     * @param height specifies the length of the new rectangle
     * @param width specifies the width of the new rectangle
     */
    public Rectangle(int width, int height, int x, int y, Color color, Color fillColor, BasicStroke stroke) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        this.fillColor = fillColor;
        this.stroke = stroke;
    }

    /**
     * Default constructor
     */
    public Rectangle() {
        this.width = 20;
        this.height = 10;
    }

    /**
     * Displays the rectangle using the specified character
     * 
     * @param c the character used to display this rectangle
     */
    @Override
    public void draw(Graphics2D g) {
        if (fillColor != null) {
            g.setColor(fillColor);
            g.fillRect(x, y, width, height);
        }
        g.setStroke(stroke);
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    @Override
    protected String getName() {
        return "Rectangle";    
    }

}
