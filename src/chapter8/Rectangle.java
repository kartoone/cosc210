package chapter8;

public class Rectangle extends Shape {

    /**
     * Specialized constructor to initialize the length and width
     * 
     * @param length specifies the length of the new rectangle
     * @param width specifies the width of the new rectangle
     */
    public Rectangle(int length, int width, String color) {
        this.length = length;
        this.width = width;
        this.color = color;
    }

    /**
     * Default constructor
     */
    public Rectangle() {
        this.length = 10;
        this.width = 20;
    }

    /**
     * Displays the rectangle using the specified character
     * 
     * @param c the character used to display this rectangle
     */
    public void display(String c) {
        String topbottom = c.repeat(width);
        String middle = c + " ".repeat(width-2) + c;
        for (int i = 0; i < length; i++) {
            if (i==0 || i==length-1) {
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
