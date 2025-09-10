
import java.util.Scanner;

public class InputExample {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // System.out.println("What is your name?");
        // String name = in.nextLine();
        // if (name.length() > 15) {
        //     System.out.println("too long");
        // } else {
        //     System.out.println(name);
        //     int nameLength = name.length();
        //     if (nameLength > 50) {
        //         System.out.println("too long!");
        //     } else if (nameLength > 45) {
        //         System.out.println("warning, this name is almost too long");
        //     } else if (nameLength > 30) {
        //         System.out.println("caution, approaching limit");
        //     } else {
        //         System.out.println(name);
        //     }
        // }

        // System.out.println("Choose from the following options:");
        // System.out.println("1. Enter your height");
        // System.out.println("2. Enter your eye color");
        // System.out.println("3. Enter your hair color");
        // System.out.println("4. Quit");
        // System.out.print("Choice? ");

        // int choice = in.nextInt();

        // Instead of a menu, we could INSTEAD ask for multiple things at once!
        System.out.println("Enter length width depth (separated by spaces):");
        double length = in.nextDouble();
        double width = in.nextDouble();
        double depth = in.nextDouble();
        double volume = length * width * depth;
        System.out.println("The volume of the box is: " + volume);

        System.out.print(length + ",");
        System.out.print(width + ",");
        System.out.print(depth + ",");
        System.out.println();


    }
}
