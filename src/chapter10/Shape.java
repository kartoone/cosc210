package chapter10;

import java.awt.Graphics2D;
import java.awt.Color;

public abstract class Shape {

    protected Color color;
    protected int width;
    protected int height;
    protected int x; // x, y coordinate of the top left of the shape
    protected int y; 

    abstract protected String getName();
    abstract protected void draw(Graphics2D g);
    /**
     * Print the properties of the shape
     */
    public void print() {
        System.out.println("This is a " + color + " " + getName() + ": " + width + "x" + height);
    }

}
