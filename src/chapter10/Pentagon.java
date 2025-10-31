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

    @Override
    protected void draw(Graphics2D g) {

    }

}
