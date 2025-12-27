package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        File file = new File("day3.txt");
        try {
           Scanner scanner = new Scanner(file);
        //    System.out.println(method1(scanner));
           System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    private static long method2(Scanner scanner) {
        long total = 0;
        while (scanner.hasNextLine()) {
            String bank = scanner.nextLine();
            int maxdigits[] = new int[12];            
            int lastpos = 0; // only need to store the most recent "max" spot we need to start from
            
            // find all the maximum digits
            for (int j=11; j>=0; j--) {
                // algorithm search first n-1 characters for largest digit ... but find the leftmost occurrence of it
                for (int i=lastpos+1; i<bank.length()-j; i++) {
                    int potmax = Integer.parseInt(""+bank.charAt(i));
                    if (potmax>maxdigits[11-j]) { // strictly greater will make sure we find the leftmost
                        maxdigits[11-j] = potmax;
                        lastpos = i;
                        if (maxdigits[11-j] == 9) {
                            break; // stop early if we hit 9
                        }
                    }
                }
            }

            String maxstr = "";
            for (int d : maxdigits) {
                maxstr += d;
            }
            total += Long.parseLong(maxstr);
        }
        return total;
    }

}
