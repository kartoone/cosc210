package chapter10;

import java.awt.Color;

public abstract class Polygon extends Shape {

    public Polygon(int sidelength, int x, int y, Color color) {
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

}
