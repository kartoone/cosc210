package chapter10;

import java.awt.Graphics2D;

public class Ellipse extends Shape {

    public Ellipse(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    protected String getName() {
        return "Ellipse";
    }

    @Override
    protected void draw(Graphics2D g) {
        System.out.println("IMAGINE THIS IS A " + this.width + "x" + this.height + " " + getName());    
    }

}
