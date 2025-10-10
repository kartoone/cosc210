package chapter8;

public class Rectangle extends Shape {

    /**
     * Specialized constructor to initialize the length and width
     * 
     * @param height specifies the length of the new rectangle
     * @param width specifies the width of the new rectangle
     */
    public Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Default constructor
     */
    public Rectangle() {
        this.width = 20;
        this.height = 10;
    }

    /**
     * Displays the rectangle using the specified character
     * 
     * @param c the character used to display this rectangle
     */
    @Override
    public void display(String c) {
        String topbottom = (c+" ").repeat(width);
        String middle = c + " ".repeat(2*width-3) + c;
        for (int i = 0; i < height; i++) {
            if (i==0 || i==height-1) {
                System.out.println(topbottom);
            } else {
                System.out.println(middle);
            }
        }
    }

    @Override
    protected String getName() {
        return "Rectangle";    
    }

}
