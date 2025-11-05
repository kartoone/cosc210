package chapter10;

import java.awt.Color;

public class Pentagon extends Polygon {

    public Pentagon(int sidelength, int x, int y, Color color) {
        super(5, sidelength, x, y, color);
    }

    @Override
    protected String getName() {
        return "Pentagon";        
    }

    
}
