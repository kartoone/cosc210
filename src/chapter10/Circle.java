package chapter10;

import java.awt.Color;

public class Circle extends Ellipse {

    public Circle(int radius, int x, int y, Color color) {
        super(2*radius, 2*radius, x, y, color);
    }

    @Override
    public String getName() {
        return "Circle";
    }

}
