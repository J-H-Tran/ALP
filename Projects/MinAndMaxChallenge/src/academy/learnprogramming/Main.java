package academy.learnprogramming;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //boolean firstNum = true;
        int minNumber = Integer.MAX_VALUE;
        int maxNumber = Integer.MIN_VALUE;

        while(true) {

            System.out.println("Enter a number: ");
            boolean isInt = scanner.hasNextInt();

            if(isInt) {

                int number = scanner.nextInt();

//                if(firstNum) {
//                    firstNum = false;
//                    minNumber = number;
//                    maxNumber = number;
//                }
                if(number > maxNumber) {
                    maxNumber = number;
                } else if(number < minNumber) {
                    minNumber = number;
                }

            } else {
                System.out.println("Not a valid number");
                break;
            }
            scanner.nextLine();

            System.out.println("Min number is: " + minNumber);
            System.out.println("Max number is: " + maxNumber);
        }
        System.out.println("Min number is: " + minNumber);
        System.out.println("Max number is: " + maxNumber);

        scanner.close();
    }
}
