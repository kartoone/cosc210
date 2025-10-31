package chapter10;

import java.awt.Color;

public class Square extends Rectangle {

    /**
     * Specialized constructor that enforces equal side lengths
     * @param sidelength the length of each side of the square
     */
    public Square(int sidelength, int x, int y, Color color) {
        super(sidelength, sidelength, x, y, color);
    }

    @Override
    protected String getName() {
        return "Square";    
    }
}
