package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int val = 4;

        printDayOfTheWeek(-1);
        printDayOfTheWeek(0);
        printDayOfTheWeek(1);
        printDayOfTheWeek(2);
        printDayOfTheWeek(3);
        printDayOfTheWeek(4);
        printDayOfTheWeek(6);

        if(val == 0) {
            System.out.println("Sunday if");
        } else if(val == 1) {
                System.out.println("Monday if");
        } else if(val == 2) {
                System.out.println("Tuesday if");
        } else if(val == 3) {
                System.out.println("Wednesday if");
        } else if(val == 4) {
                System.out.println("Thursday if");
        } else if(val == 5) {
                System.out.println("Friday if");
        } else if(val == 6) {
                System.out.println("Saturday if");
        } else {
            System.out.println("Invalid day if");
        }
    }

    public static void printDayOfTheWeek(int val) {

        switch(val) {
            case 0:
                System.out.println("Sunday case");
                break;
            case 1:
                System.out.println("Monday case");
                break;
            case 2:
                System.out.println("Tuesday case");
                break;
            case 3:
                System.out.println("Wednesday case");
                break;
            case 4:
                System.out.println("Thursday case");
                break;
            case 5:
                System.out.println("Friday case");
                break;
            case 6:
                System.out.println("Saturday case");
                break;
            default:
                System.out.println("Invalid day case");
                break;
        }
    }
}
