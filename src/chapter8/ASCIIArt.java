package chapter8;

public class ASCIIArt {

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(4, 5, "Red");
        r1.print();
        r1.display("*");
        Square s1 = new Square(5, "Blue");
        s1.display("x");
        s1.print();
    }
}
