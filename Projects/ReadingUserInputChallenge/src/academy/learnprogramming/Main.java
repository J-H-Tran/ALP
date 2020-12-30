package academy.learnprogramming;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int cnt = 0;

        while(true) {

            int order = cnt + 1;
            System.out.println("Enter a value for #" + order);

            boolean hasNextInt = scanner.hasNextInt();

            if(hasNextInt) {

                int number = scanner.nextInt();
                cnt++;
                sum += number;

                if(cnt == 10) {
                    break;
                }
            } else {
                System.out.println("Invalid Number Entered");
            }
            /*
            * If the user was to enter an invalid number the else statement would
            * run and print. However, since the user did enter content it would
            * need to be read and removed from the scanner before additional content
            * is entered.
            *
            * And since the nextLine() call handles that by reading input and NOT storing
            * it it needs to be after the else statement and not in the if statement.
            * Otherwise, the invalid content would not be removed and our application
            * wouldn't run as desired.
            * */
            scanner.nextLine();

        }
        System.out.println("Sum is: " + sum);
        scanner.close();
    }
}
