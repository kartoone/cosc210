package chapter10;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Polygon extends Shape {

    protected int numSides;

    public Polygon(int numSides, int sidelength, int x, int y, Color color) {
        this.numSides = numSides;
        this.width = sidelength;
        this.height = sidelength;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    protected String getName() {
        return "Polygon";        
    }

    // Pentagon is easier to draw inside a circle by finding the FIVE coordinates 20% (1/5th) around the circle ...
    // since there are 2pi radians in a circle, each vertex is 2pi/5 radians apart

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(color);

        // Build a pentagon on a circle inside the bounding box
        // by calculating the radius of the circle as half the width
        // This becomes the center of the circle.
        double cx = x + width / 2.0;
        double cy = y + height / 2.0;
        double r  = Math.min(width, height) / 2.0; // technically don't need to do min here since width==height for polygons

        double[] px = new double[numSides];
        double[] py = new double[numSides];

        double start = -Math.PI / 2.0; // top vertex up
        for (int i = 0; i < numSides; i++) {
            double a = start + i * 2.0 * Math.PI / numSides;
            px[i] = cx + r * Math.cos(a);
            py[i] = cy + r * Math.sin(a);
        }

        // Connect every consecutive vertex together
        for (int i = 0; i < numSides; i++) {
            g.drawLine((int)Math.round(px[i]), (int)Math.round(py[i]),
                        (int)Math.round(px[i+1]), (int)Math.round(py[i+1]));
        }
    }

}
