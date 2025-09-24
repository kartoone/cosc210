package chapter6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArraysVsArrayLists {

    public static int theRealSize = 0;

    public static void main(String[] args) throws FileNotFoundException {
        //File f = new File("C:\\Windows\\WinSxS\\amd64_microsoft-windows-s..e.desktop.searchapp_31bf3856ad364e35_10.0.19041.6033_none_02e652204c53f8c6\\3.txt");
        File f= new File("/Users/briantoone/Desktop/sensorData.txt");
        Scanner fileIn = new Scanner(f);

        String arrayWords[] = new String[1_000_000_000];
        ArrayList<String> listWords = new ArrayList<>();
        populateArray(fileIn, arrayWords);
        fileIn = new Scanner(f);    // reset the Scanner by creating a new one! ... Unfortunately, the reset() method only resets the configuration options but doesn't return us to the beginning of the file.
        populateList(fileIn, listWords);

        String longestWord1 = findLongestWord(arrayWords);
        String longestWord2 = findLongestWord(listWords);
        System.out.println(longestWord1);
        System.out.println(longestWord2);
    }

    private static String findLongestWord(ArrayList<String> listWords) {
        // 1. Initialize the longest word
        String longestWord = "";

        // 2. Iterate through the list and see if the current word is longer
        Iterator<String> it = listWords.iterator();
        while (it.hasNext()) {
            String word = it.next();
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        // Alternative approach that works well with ArrayLists
        //for (String w : listWords) {
        //    if (w.length() > longestWord.length()) {
        //        longestWord = w;
        //    }
        //}

        // 3. Return the longest word
        return longestWord;
    }

    private static String findLongestWord(String[] arrayWords) {
        // 1. initialize the longest word
        String longestWord = "";

        // 2. iterate through the array
        for (int i=0; i<theRealSize; i++) {
            String w = arrayWords[i];
            if (w.length() > longestWord.length()) {
                longestWord = w;
            }
        }

        // 3. return the longest word
        return longestWord;
    }

    private static void populateList(Scanner fileIn, ArrayList<String> listWords) {
        while (fileIn.hasNext()) {
            String word = fileIn.next();
            listWords.add(word);
        }
    }

    private static void populateArray(Scanner fileIn, String[] arrayWords) {
        int i=0;
        while (fileIn.hasNext()) {
            String word = fileIn.next();
            arrayWords[i++] = word;
        }
        theRealSize = i;
    }


}
