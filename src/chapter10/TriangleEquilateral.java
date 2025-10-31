package chapter10;

import java.awt.Color;
import java.awt.Graphics2D;

public class TriangleEquilateral extends Triangle {

    public TriangleEquilateral(int sidelength, int x, int y, Color color) {
        super(sidelength, sidelength, x, y, color);
    }

    @Override
    protected void draw(Graphics2D g) {

    } 

}
