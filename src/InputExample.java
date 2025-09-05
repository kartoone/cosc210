
import java.util.Scanner;

public class InputExample {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = in.nextLine();
        if (name.length() > 15) {
            System.out.println("too long");
        } else {
            System.out.println(name);
            int nameLength = name.length();
            if (nameLength > 50) {
                System.out.println("too long!");
            } else if (nameLength > 45) {
                System.out.println("warning, this name is almost too long");
            } else if (nameLength > 30) {
                System.out.println("caution, approaching limit");
            } else {
                System.out.println(name);
            }
        }

        System.out.println("Choose from the following options:");
        System.out.println("1. Enter your height");
        System.out.println("2. Enter your eye color");
        System.out.println("3. Enter your hair color");
        System.out.println("4. Quit");
        System.out.print("Choice? ");

        int choice = in.nextInt();

    }
}
