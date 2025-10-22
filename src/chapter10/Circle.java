package chapter10;

public class Circle extends Ellipse {

    public Circle(int radius, String color) {
        super(2*radius, 2*radius, color);
    }

    @Override
    public String getName() {
        return "Circle";
    }

}
