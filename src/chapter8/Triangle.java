package chapter8;

public abstract class Triangle extends Shape {

    public Triangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    protected String getName() {
        return "Triangle";        
    }

    abstract protected void display(String c);

}
