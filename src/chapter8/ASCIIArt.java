package chapter8;

public class ASCIIArt {

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(5, 4, "Red");
        Square s1 = new Square(4, "Blue");
        Triangle t1 = new TriangleEquilateral(5, "Yellow");
        Ellipse e1 = new Ellipse(5, 4, "Green");
        Circle c1 = new Circle(5, "Orange");
        Shape shapes[] = {r1, s1, t1, e1, c1};
        for (Shape shape : shapes) {
            shape.print();
            shape.display("*");
        }
        


    }
}
