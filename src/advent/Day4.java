package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        File file = new File("day4.txt");
        try {
            Scanner scanner = new Scanner(file);
            // first count the rows and columns
            int rows = 0;
            String row = null;
            while (scanner.hasNextLine()) {
                row = scanner.nextLine();
                rows++;
            }
            if (row == null)
                System.out.println("Invalid input");
            int cols = row.length();
            byte grid[][] = new byte[rows][cols];
            scanner.close();

            // now go through the file again and this time populate everypos
            scanner = new Scanner(file);
            for (int r = 0; r < rows; r++) {
                row = scanner.nextLine();
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = (row.charAt(c) == '@') ? (byte) 1 : (byte) 0;
                }
            }
            //System.out.println(method1(grid));
            int grandtotal = 0;
            long currentremove = 0;
            do {
                currentremove = method2(grid);
                grandtotal += currentremove;
            } while (currentremove>0);
            System.out.println(grandtotal);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    @SuppressWarnings("unused")
	private static void displayGrid(byte grid[][]) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }

    }

    @SuppressWarnings("unused")
	private static long method1(byte grid[][]) {
        long total = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c]==1 && countNeighbors(grid, r, c)<4) {
                    total++;
                }
            }
        }
        return total;
    }
    
    private static long method2(byte grid[][]) {
        byte gridc[][] = new byte[grid.length][grid[0].length];
        long total = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c]==1 && countNeighbors(grid, r, c)<4) {
                    gridc[r][c] = (byte) 0; // remove roll
                    total++;
                } else {
                    gridc[r][c] = grid[r][c]; // leave unchanged
                }
            }
        }
        // update the grid that was passed in with the current rolls status
        for (int i = 0; i < gridc.length; i++) {
            for (int j = 0; j < gridc.length; j++) {
                grid[i][j] = gridc[i][j];
            } 
        }
        return total;
    }



    private static int countNeighbors(byte[][] grid, int r, int c) {
        int count = 0;
        if (r>0) {
            // only count previous row if we are beyond first row
            if (c>0) {
                // only count previous col if we are beyond first col
                count += (grid[r-1][c-1] == 1) ? 1 : 0;
            }
            count += (grid[r-1][c] == 1) ? 1 : 0;
            if (c<grid[0].length-1) {
                // only count next col if we are prior to last col
                count += (grid[r-1][c+1] == 1) ? 1 : 0;
            }
        }
        // no matter what we can count the current row BUT DO NOT COUNT THE CELL ITSELF!
        if (c>0) {
            // only count previous col if we are beyond first col
            count += (grid[r][c-1] == 1) ? 1 : 0;
        }
        if (c<grid[0].length-1) {
            // only count next col if we are prior to last col
            count += (grid[r][c+1] == 1) ? 1 : 0;
        }
        if (r<grid.length-1) {
            // only count next row if we are prior to last row
            if (c>0) {
                // only count previous col if we are beyond first col
                count += (grid[r+1][c-1] == 1) ? 1 : 0;
            }
            count += (grid[r+1][c] == 1) ? 1 : 0;
            if (c<grid[0].length-1) {
                // only count next col if we are prior to last col
                count += (grid[r+1][c+1] == 1) ? 1 : 0;
            }
        }
        return count;
    }

    @SuppressWarnings("unused")
	private static long method2(Scanner scanner) {
        long total = 0;
        while (scanner.hasNextLine()) {
            String bank = scanner.nextLine();
            int maxdigits[] = new int[12];
            int lastpos = 0; // only need to store the most recent "max" spot we need to start from

            // find all the maximum digits
            for (int j = 11; j >= 0; j--) {
                // algorithm search first n-1 characters for largest digit ... but find the
                // leftmost occurrence of it
                for (int i = lastpos + 1; i < bank.length() - j; i++) {
                    int potmax = Integer.parseInt("" + bank.charAt(i));
                    if (potmax > maxdigits[11 - j]) { // strictly greater will make sure we find the leftmost
                        maxdigits[11 - j] = potmax;
                        lastpos = i;
                        if (maxdigits[11 - j] == 9) {
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
