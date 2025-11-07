package chapter10;

import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape {

    // coordinates for the endpoint of our line
    // we will use Shape's x, y for the starting point
    protected int x2;
    protected int y2; 

    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.x = x1;
        this.y = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = Math.abs(x2-x1);
        this.height = Math.abs(y2-y1);
    }

    @Override
    protected String getName() {
        return "Line";
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(color);  
        g.drawLine(x, y, x2, y2);  
    }

}
