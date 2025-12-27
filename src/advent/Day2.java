package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        File file = new File("day2.txt");
        try {
           Scanner scanner = new Scanner(file);
           //System.out.println(method1(scanner));
           System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    @SuppressWarnings("unused")
	private static long method1(Scanner scanner) {
        String ranges = scanner.nextLine();
        long total = 0;
        String[] parts = ranges.split(",");
        for (String p : parts) {
            String[] endpoints = p.split("-");
            long start = Long.parseLong(endpoints[0]);
            long end = Long.parseLong(endpoints[1]);
            for (long l = start; l <= end; l++) {
                String strl = ""+l;
                if (l < 10 || (strl.length()%2 != 0)) {
                    // single digit numbers cannot be invalid
                    // odd length id's cannot be invalid
                    continue;
                }
                // now split into two halves
                String str1 = strl.substring(0, strl.length()/2);
                String str2 = strl.substring(strl.length()/2);
                if (str1.equals(str2)) {
                    total += l;
                }
            }
        }
        return total;
    }

    private static long method2(Scanner scanner) {
        String ranges = scanner.nextLine();
        long total = 0;
        String[] parts = ranges.split(",");
        for (String p : parts) {
            String[] endpoints = p.split("-");
            long start = Long.parseLong(endpoints[0]);
            long end = Long.parseLong(endpoints[1]);
            for (long l = start; l <= end; l++) {
                String strl = ""+l;
                if (l < 10) {
                    // single digit numbers cannot be invalid
                    // odd length id's cannot be invalid
                    continue;
                }
                // now split into equal length parts of length ss
                for (int ss=strl.length(); ss>=2; ss--) {
                    if (strl.length()%ss != 0)
                        continue;
                    int splitlength = strl.length()/ss;
                    String str1 = strl.substring(0, splitlength);
                    // now split rest of string up into parts of same size 
                    boolean allgood = true;
//                    boolean ranonce = false;
                    for (int starti = splitlength; starti <= strl.length()-splitlength; starti+=splitlength) {
 //                       ranonce = true;
                        String str2 = strl.substring(starti, starti+splitlength);
                        if (!str1.equals(str2)) {
                            allgood = false;
                            break;
                        } 
                    }
                    //if (allgood && ranonce) {
                    if (allgood) {
                        total += l;
                        break; // don't try and split again
                    }
                } 
            }
        }
        return total;
    }
}
