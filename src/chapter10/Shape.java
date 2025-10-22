package chapter10;

import java.awt.Graphics2D;

public abstract class Shape {

    protected String color;
    protected int width;
    protected int height;

    abstract protected String getName();
    abstract protected void draw(Graphics2D g);
    /**
     * Print the properties of the shape
     */
    public void print() {
        System.out.println("This is a " + color + " " + getName() + ": " + width + "x" + height);
    }

}
