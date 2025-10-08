package chapter8;

public abstract class Shape {

    protected String color;
    protected int length;
    protected int width;

    abstract protected String getName();

    /**
     * Print the properties of the shape
     */
    public void print() {
        System.out.println("This is a " + color + " " + getName() + ": " + length + "x" + width);
    }

}
