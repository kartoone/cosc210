package chapter6;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArraysVsArrayLists {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("C:\\Windows\\WinSxS\\amd64_microsoft-windows-s..e.desktop.searchapp_31bf3856ad364e35_10.0.19041.6033_none_02e652204c53f8c6\\3.txt");
        Scanner fileIn = new Scanner(f);

        String arrayWords[] = new String[1_000_000_000];
        ArrayList<String> listWords = new ArrayList<>();
        populateArray(fileIn, arrayWords);
        populateList(fileIn, listWords);

        
    }

    private static void populateList(Scanner fileIn, ArrayList<String> listWords) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'populateList'");
    }

    private static void populateArray(Scanner fileIn, String[] arrayWords) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'populateArray'");
    }

}
