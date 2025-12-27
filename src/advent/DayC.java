package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayC {

    static class Present {
        int shape[][] = new int[3][3]; // at least for part 1, all shapes are 3x3
        int area = 0;
        
        // parse the line from the input file
        public Present(String lines) {
            String parts[] = lines.split("\n");
            for (int r=0; r<3; r++) {
                String line = parts[r];
                for (int c=0; c<3; c++) {
                    shape[r][c] = line.charAt(c)=='#' ? 1 : 0;
                    area += line.charAt(c)=='#' ? 1 : 0;
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public String toString() {
            String out = "";
            for (int[] row : shape) {
                out += java.util.Arrays.toString(row) + "\n";

            }
            return out;
        }

    }

    static class Region {
        int pad[][]; // can use this as "scratch" area when trying to place all the presents
        int quantities[];
        
        // parse the line from the input file
        public Region(String line) {
            int xloc = line.indexOf("x");
            int colloc = line.indexOf(":");
            int rows = Integer.parseInt(line.substring(0,xloc));
            int cols = Integer.parseInt(line.substring(xloc+1,colloc));
            pad = new int[rows][cols];

            String parts[] = line.substring(colloc+2).split(" ");
            quantities = new int[parts.length];
            int i = 0;
            for (String p:parts) {
                quantities[i++] = Integer.parseInt(p);
            }
        }

        public boolean canFit() {
            // first create temp array of all the presents we need to deal with
            // total number of rows and cols required
            int totalarea = 0;
            
            // peeked at reddit hints and based on the comments realized that sooo many of the 
            // combinations of presents required have way too much AREA to possibly fit under the tree

            // my first attempt was to simply count all the ones with less area 
            // and figured there might be a few that still wouldn't fit
            // but as it turns out all the quantities required with less area than tree region area
            // worked and that was the solution!
            for (int i = 0; i < quantities.length; i++) {
                totalarea+=allpresents.get(i).area*quantities[i];
            }

            return totalarea<pad.length*pad[0].length;

            // consider two total presents
            // 00 means - all presents in original orientation (but moved to all possible positions)
            // 01 means - first present in original orientation (but second present rotated 90 ccw)
            // 02 means - first present in original orientation (but second present rotated 180 ccw)
            // 03 means - first present in original orientation (but second present rotated 270 ccw)
            // 04 means - first present in original orientation (but second present rotated 90 ccw and flipped horizontally)
            // 05 means - first present in original orientation (but second present rotated 180 ccw and flipped horizontally)
            // 06 means - first present in original orientation (but second present rotated 270 ccw and flipped horizontally)
            // 07 means - first present in original orientation (but second present rotated 90 ccw and flipped vertically)
            // 08 means - first present in original orientation (but second present rotated 180 ccw and flipped vertically)
            // 09 means - first present in original orientation (but second present rotated 270 ccw and flipped vertically)
            // 0A means - first present in original orientation (but second present flipped horizontally)
            // 0B means - first present in original orientation (but second present flipped vertically)
            // 10 means - second present in original orientation (but first present rotated 90 ccw)
            // etc...
            // total number of combos: 16*16 = 256

            // consider three total presents:
            // 000
            // 001
            // 002
            // 003
            // 004
            // 005
            // 006
            // 007
            // 008
            // 009
            // 00A
            // 00B
            // 010
            // 011
            // 012
            // 013
            // 014
            // 015
            // 016
            // 017
            // 018
            // 019
            // 01A
            // 01B
            // ...
            // 0BB (final combo where first is kept unmanipulated)
            // ... 
            // BBB (true final combo where we have tried everything) 
            // total number of combos: 16*16*16 = 

            // This will work great on the sample input but the REAL input has a few hundred presents = more combos than the number of atoms in the universe by a LOT

            // what about filtering total number of rows and cols required

            // ok now we need to systematically try every present in every possible rotation and flipped (i.e., orientation)
            // in every possible position on the grid with every other present in every possible rotation

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public String toString() {
            return pad.length+"x"+pad[0].length+":" + java.util.Arrays.toString(quantities);
        }
    }
    public static void main(String[] args) {
        File file = new File("dayC.txt");
        try {
           Scanner scanner = new Scanner(file);
           System.out.println(method1(scanner));
           //System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }
    
    static ArrayList<Present> allpresents = new ArrayList<>();
    static ArrayList<Region> allregions = new ArrayList<>();

    // breadth first search of trying all the different combos of all the buttons
    private static void readfile(Scanner scanner) {

        // read in the file
        String line = "";
        String lines = "";
        while (true) {
            line = scanner.nextLine();
            if (line.contains("x"))
                break;
            else if (line.contains(":"))
                continue; // skip the index line we are just adding them in order
            else if (line.length()==0) {
                allpresents.add(new Present(lines));
                lines = "";
            } else {
                lines += line + "\n"; // "hanging" newline not really a problem but just remember it's there when constructing present
            }
        }
        Region r = new Region(line);
        allregions.add(r);

        // ok now we have the "regions" and quantities
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            r = new Region(line);
            allregions.add(r);
        } 

    }
        
    private static long method1(Scanner scanner) {
        readfile(scanner);

        long total = 0;
        for (Region region : allregions) {
            if (region.canFit())
                total++;
        }

        return total;

    }

}
