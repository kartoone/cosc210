package chapter10;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pentagon extends Polygon {

    public Pentagon(int sidelength, int x, int y, Color color) {
        super(sidelength, x, y, color);
    }

    @Override
    protected String getName() {
        return "Pentagon";        
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

        double[] px = new double[5];
        double[] py = new double[5];

        double start = -Math.PI / 2.0; // top vertex up
        for (int i = 0; i < 5; i++) {
            double a = start + i * 2.0 * Math.PI / 5.0;
            px[i] = cx + r * Math.cos(a);
            py[i] = cy + r * Math.sin(a);
        }

        // Pentagon connects every consecutive vertex together
        int[] order = {0, 1, 2, 3, 4, 0};
        for (int i = 0; i < order.length - 1; i++) {
            int a = order[i], b = order[i + 1];
            g.drawLine((int)Math.round(px[a]), (int)Math.round(py[a]),
                        (int)Math.round(px[b]), (int)Math.round(py[b]));
        }
    }
}
