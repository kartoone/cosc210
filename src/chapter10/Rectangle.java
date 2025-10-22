package chapter10;

import java.awt.Graphics2D;

public class Rectangle extends Shape {

    /**
     * Specialized constructor to initialize the length and width
     * 
     * @param height specifies the length of the new rectangle
     * @param width specifies the width of the new rectangle
     */
    public Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
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
        g.drawRect(0, 0, width, height);
    }

    @Override
    protected String getName() {
        return "Rectangle";    
    }

}
