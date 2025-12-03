package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {

    static int method1(Scanner scanner) {
        int dial = 50;
        int password = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char d = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));
            if (d=='L')
                dial = (dial - amount)%100;
            else
                dial = (dial + amount)%100;

            if (dial == 0) {
                password++;
            }
        }
        return password;
 
    }

    static int method2(Scanner scanner) {
        int dial = 50;
        int password = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char d = line.charAt(0);
            int amountr = Integer.parseInt(line.substring(1));
            while (amountr > 0) {
                if (d=='L')
                    dial = (dial - 1)%100;
                else
                    dial = (dial + 1)%100;

                if (dial == 0) {
                    password++;
                }
                amountr--;
            }
        }
        return password;
    }

    public static void main(String[] args) {
        
        File file = new File("day1.txt");
        try {
           Scanner scanner = new Scanner(file);
           //System.out.println(method1(scanner));
           System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }

    }

}
