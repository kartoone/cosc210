package chapter10;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Pencil extends Shape {

    // coordinates for the endpoint of our line
    // we will use Shape's x, y for the starting point
    protected ArrayList<Integer> xcoords = new ArrayList<Integer>(); 
    protected ArrayList<Integer> ycoords = new ArrayList<Integer>(); 

    public Pencil(int x1, int y1, Color color, BasicStroke stroke) {
        this.x = x1;
        this.y = y1;
        this.color = color;
        this.stroke = stroke;
    }

    @Override
    protected String getName() {
        return "Pencil";
    }

    public void addPoint(int x, int y) {
        xcoords.add(x);
        ycoords.add(y);
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(color);  
        g.setStroke(stroke);
        g.drawPolyline(
            xcoords.stream().mapToInt(Integer::intValue).toArray(),
            ycoords.stream().mapToInt(Integer::intValue).toArray(),
            xcoords.size()
        );
    }

}
