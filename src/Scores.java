import java.util.Scanner;

public class Scores {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many students are in your class?");
        int numStudents = in.nextInt();
        double scores[] = new double[numStudents];
        for (int i = 0; i < scores.length; i++) {
            System.out.println("What is the score for Student #" + i);
            scores[i] = in.nextDouble();
        }

        double total = 0.;
        for (double d : scores) {
            total = total + d; 
        }

        if (numStudents == 0) {
            System.out.println("There are no scores.");
        } else {
            double theAverage = total / numStudents;
            System.out.println("The average is: " + theAverage);
        }

    }
}
