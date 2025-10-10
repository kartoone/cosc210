package chapter8;

public abstract class Shape {

    protected String color;
    protected int width;
    protected int height;

    abstract protected String getName();
    abstract protected void display(String c);
    /**
     * Print the properties of the shape
     */
    public void print() {
        System.out.println("This is a " + color + " " + getName() + ": " + width + "x" + height);
    }

}
