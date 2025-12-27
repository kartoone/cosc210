package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        File file = new File("day6.txt");
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
        int nums[][] = null;
        String ops[] = null;
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String parts[] = line.split("\\s+");
            if (nums==null) {
                nums = new int[4][parts.length];
                ops = new String[parts.length];
            }
            // now parse and stuff each "part" into the columns
            if (row==4) {
                ops = parts; // just reuse the last parts array since it won't be overwritten
            } else {
                for (int c=0; c<parts.length; c++) {
                    nums[row][c] = Integer.parseInt(parts[c]);
                }
            }
            row++;
        }
        long total = 0;

        // now do all the ops column by column
        for (int c=0;c<nums[0].length;c++) {
            if (ops[c].equals("*")) {
                long result = 1;
                for (int r=0; r<4; r++) {
                    result *= nums[r][c];
                }
                total+=result;
            } else {
                long result = 0;
                for (int r=0; r<4; r++) {
                    result += nums[r][c];
                }
                total+=result;
            }
        }
        return total;
    }

    private static long method2(Scanner scanner) {
        long total = 0;
        char chars[][] = null;
        char ops[] = null;
        int r = 0;

        // first just read in all the chars
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (chars == null) {
                chars = new char[4][row.length()];
                ops = new char[row.length()];
            }
            if (r==4) {
                for (int i=0; i<row.length(); i++) {
                    ops[i] = row.charAt(i);
                } 
            } else {
                for (int i=0; i<row.length(); i++) {
                    chars[r][i] = row.charAt(i);
                } 
            }
            r++;
        }

        // now we need to read from right to left queuing up the numbers until we get to the op
        ArrayList<Integer> queue = new ArrayList<>();
        for (int c=chars[0].length-1; c>=0; c--) {
            String numstr = "" + chars[0][c] + chars[1][c] + chars[2][c] + chars[3][c];
            if (numstr.trim().length()==0)
                continue; // skip the blank column
            Integer num = Integer.parseInt(numstr.trim());
            if (ops[c] != ' ') {
                // queue the last num
                queue.add(num);

                // do the op
                if (ops[c]=='+') {
                    long result = 0;
                    for (Integer next : queue) {
                        result += next;
                    }
                    total += result;
                } else {
                    long result = 1;
                    for (Integer next : queue) {
                        result *= next;
                    }
                    total += result;
                }
                // reset the queue
                queue = new ArrayList<>();
            } else {
                // just queue the current num, more nums to follow
                queue.add(num);
            }
        } 

        return total;
    }
}
