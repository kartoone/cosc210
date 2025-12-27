package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        File file = new File("day7.txt");
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
        long total = 0;

        // first read in the grid
        ArrayList<char[]> gridlist = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] row = new char[line.length()];
            for (int c=0; c<line.length(); c++)
                row[c] = line.charAt(c);
            gridlist.add(row);
        }

        // now convert to 2d array
        char grid[][] = new char[gridlist.size()][gridlist.get(0).length];
        for (int r = 0; r<gridlist.size(); r++) {
            grid[r] = gridlist.get(r);
        }

        // now do the simulation
        // simply look at what's below the current col to see how to update the next col
        for (int r = 0; r<gridlist.size()-1; r++) { 
            char[] cur = grid[r];
            char[] next = grid[r+1];
            for (int c=0; c<cur.length; c++) {
                if (cur[c]=='|' && next[c]=='^') {
                    total++;
                    if (c-1>=0)
                        next[c-1] = '|';
                    if (c+1<cur.length)
                        next[c+1] = '|';
                } else if (cur[c]=='S' || cur[c]=='|') {
                    next[c] = '|';
                }
            }
        }

        return total;
    }

    private static long method2(Scanner scanner) {
        // first read in the grid
        ArrayList<char[]> gridlist = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] row = new char[line.length()];
            for (int c=0; c<line.length(); c++)
                row[c] = line.charAt(c);
            gridlist.add(row);
        }

        // now convert to 2d array
        char grid[][] = new char[gridlist.size()][gridlist.get(0).length];
        for (int r = 0; r<gridlist.size(); r++) {
            grid[r] = gridlist.get(r);
        }

        // now do the simulation (RECURSIVELY) ... two different version of cur will end up getting passed ... 
        // recursion stops if row reaches the length of the grid-1
        // simply look at what's below the current col to see how to update the next col
        char[] cur = gridlist.get(0); // bootstrap so that we don't have to deal with S again in our recursion
        char[] next = gridlist.get(1);
        for (int c=0; c<cur.length; c++) {
            if (cur[c]=='S') {
                next[c] = '|';
            }
        }
        return advance(grid, next, 1);
    }

    private static Map<String, Long> alreadytried = new HashMap<>();

    private static long advance(char grid[][], char cur[], int r) {
        if (r==grid.length-1) {
            System.out.println(cur);
            return 1; // base case
        } 
        long total = 0; // recursive step ... make a copy of the next row so that the for loop doesn't send multiple '|' through the grid
        char next[] = java.util.Arrays.copyOf(grid[r+1], grid[r+1].length);
        //char next[] = grid[r+1];
        for (int c=0; c<cur.length; c++) {
            if (cur[c]=='|') {
                if (next[c]=='^') {
                    if (c-1>=0) {
                        char oldnext = next[c-1];
                        next[c-1] = '|';
                        String nextstr = ""+(r+1)+java.util.Arrays.toString(next);
                        if (!alreadytried.containsKey(nextstr)) {
                            long thisbranch = advance(grid, next, r+1);
                            total = thisbranch;
                            alreadytried.put(nextstr, thisbranch);
                        } else {
                            total = alreadytried.get(nextstr); 
                        }
                        next[c-1] = oldnext;
                    }
                    if (c+1<cur.length) {
                        char oldnext = next[c+1];
                        next[c+1] = '|';
                        String nextstr = ""+(r+1)+java.util.Arrays.toString(next);
                        if (!alreadytried.containsKey(nextstr)) {
                            long thisbranch = advance(grid, next, r+1);
                            total += thisbranch;
                            alreadytried.put(nextstr, thisbranch);
                        } else {
                            total += alreadytried.get(nextstr); 
                        }
                        next[c+1] = oldnext;
                    }
                    
                } else {
                    // if we have a space below just propogate
                    next[c] = '|';
                    String nextstr = ""+(r+1)+java.util.Arrays.toString(next);
                    if (!alreadytried.containsKey(nextstr)) {
                        total = advance(grid, next, r+1);
                    } else {
                        total = alreadytried.get(nextstr);
                    }
                }
                break;
            }
        }
        return total;
    }
}
