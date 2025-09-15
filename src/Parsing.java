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
        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            Scanner lineIn = new Scanner(line);
            lineIn.useDelimiter(",");
            String n = lineIn.next();
            int s = Integer.parseInt(lineIn.next());
            String m = lineIn.next();
            String cl = lineIn.next();
            System.out.println(n + "-" + s + "-" + m + "-" + cl);
        }
    }
}
