package chapter10;

import java.awt.Color;

public class Hexagon extends Polygon {

    public Hexagon(int sidelength, int x, int y, Color color) {
        super(6, sidelength, x, y, color);
    }

    @Override
    protected String getName() {
        return "Pentagon";        
    }

    
}
