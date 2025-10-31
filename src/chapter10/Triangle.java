package chapter10;

import java.awt.Color;

public abstract class Triangle extends Shape {

    public Triangle(int width, int height, int x, int y, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    protected String getName() {
        return "Triangle";        
    }

}
