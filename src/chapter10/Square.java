package chapter10;

public class Square extends Rectangle {

    /**
     * Specialized constructor that enforces equal side lengths
     * @param sidelength the length of each side of the square
     */
    public Square(int sidelength, String color) {
        super(sidelength, sidelength, color);
    }

    @Override
    protected String getName() {
        return "Square";    
    }
}
