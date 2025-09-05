package com.mycompany.app;

import java.util.Scanner;

public class Weather {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the temperature? ");
        double temp = in.nextDouble();
        in.nextLine();
        System.out.println("Enter the weather conditions: ");
        String conditions = in.nextLine();
        if (temp >= 80) {
            System.out.println("It is HOT");
        } else if (temp >= 50) {

        } else {

        }
        System.out.println(conditions);
        if (conditions.equalsIgnoreCase("sunny")) {

        }
        in.close();
    }
}
