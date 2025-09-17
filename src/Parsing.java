import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parsing {
    public static void main(String[] args) throws FileNotFoundException {
        // Example parsing a String hard-coded in the program
        String name = "Brian Richard Toone";
        Scanner strIn = new Scanner(name);
        while (strIn.hasNext()) {
            String namePart = strIn.next();
            System.out.println(namePart);
        }

        // Example of using a Scanner to parse a file
        File f = new File("scores.csv");
        Scanner fileIn = new Scanner(f);
        double total = 0.0;
        int numScores = 0; 
        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            String parts[] = line.split(",");
            System.out.println(parts[0] + "-" + parts[1] + "-" + parts[2] + "-" + parts[3]);            
            if (isInteger(parts[1])) {
                total = total + Integer.parseInt(parts[1]);
                numScores++;
            }
            //Scanner lineIn = new Scanner(line);
            //lineIn.useDelimiter(",");
            //String n = lineIn.next();
            //String scr = lineIn.next();
            //if (!isInteger(scr)) {
            //    continue; // recover from bad data and continue
            //    // System.out.println("There is something wrong with your file");
            //    // break; // give up and let the user figure it out
            //}
            //int s = Integer.parseInt(scr);
            //String m = lineIn.next();
            //String cl = lineIn.next();
            //System.out.println(n + "-" + s + "-" + m + "-" + cl);
        }
        System.out.println(total/numScores);
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }   
}
