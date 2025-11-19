package chapter10;

import java.awt.BasicStroke;
import java.awt.Color;

public class Square extends Rectangle {

    /**
     * Specialized constructor that enforces equal side lengths
     * @param sidelength the length of each side of the square
     * @param stroke 
     */
    public Square(int sidelength, int x, int y, Color color, Color fillColor, BasicStroke stroke) {
        super(sidelength, sidelength, x, y, color, fillColor, stroke);
    }

    @Override
    protected String getName() {
        return "Square";    
    }
}
