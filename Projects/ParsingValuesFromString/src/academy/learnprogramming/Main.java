package academy.learnprogramming;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        String numberAsString = "2018";
//        System.out.println("numberAsString = " +numberAsString);
//
//        int number = Integer.parseInt(numberAsString);
//        System.out.println("number = " + number);
//
//        System.out.println("\n\n");
//        numberAsString += 1;
//        number += 1;
//        System.out.println("numberAsString = " +numberAsString);
//        System.out.println("number = " + number);

        // Handling User Input

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your year of birth");

        // to check if the user input the proper data type, in this case is it a proper int?
        boolean hasNextInt = sc.hasNextInt();   // checks to see if the next input is an integer and keeps it in the scanner

        if(hasNextInt) {

            int yearOfBirth = sc.nextInt();
            sc.nextLine();  // to handle the extra new line that is not capture when user inputs a numerical value

            System.out.println("Enter your name");
            String name = sc.nextLine();

            int age = 2020 - yearOfBirth;

            if(age >= 0 && age <=100) {
                System.out.println("Your name is " + name + " and you are " + age + " years old.");
            } else {
                System.out.println("Invalid year of birth entered.");
            }
        } else {
            System.out.println("You did not enter a valid value for year of birth");
        }

        sc.close(); // don't forget to always close your scanner when you are no longer using it

    }
}
