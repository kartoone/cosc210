package chapter7;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter a file path below:");
            String filename = in.nextLine();
            if (filename.equals("quit")) {
                break;
            }
            try {
                File f = new File(filename);
                Scanner fileIn = new Scanner(f);
                while (fileIn.hasNextLine()) {
                    System.out.println(fileIn.nextLine());
                }
            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("uh-oh couldn't find your file: " + filename);
            }
        }

        System.out.println("Enter a bunch of numbers separated by spaces");
        ArrayList<Integer> nums = new ArrayList<>();
        while(in.hasNextInt()) {
            nums.add(in.nextInt());
        }
        double average = calculateAverage(nums);
        System.out.println("The average of your nums is: " + average);
    }

    private static double calculateAverage(ArrayList<Integer> nums) throws Exception {
        if (nums.size()==0) {
            throw new Exception("Unimplemented method 'calculateAverage'");
        }
        double total = 0;
        for (int n : nums) {
            total+=n;
        }
        return total/nums.size();

    }


}
