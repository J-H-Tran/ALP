package academy.learnprogramming;


public class Main {

    public static void main(String[] args) {

//        for(int i = 2; i <= 8; i++) {
//            double j = i;
//            System.out.println("10,000 at " + j + " interest rate = " + String.format("%.2f", calculateInterest(10000, j)));
//        }
//        System.out.println("*****");
//        for(int i = 8; i >= 2; i--) {
//            double j = i;
//            System.out.println("10,000 at " + j + " interest rate = " + String.format("%.2f", calculateInterest(10000, j)));
//        }

//        int cntPrime = 0;
//        for(int i = 1; i < 10000; i++) {
//
//            if(isPrime(i)) {
//                cntPrime++;
//                System.out.println(i + " is a prime number");
//                if(cntPrime == 7) {
//                    System.out.println(cntPrime + " prime numbers found, exiting for loop");
//                    break;
//                }
//            }
//        }

        int sum = 0;
        int cnt = 0;

        for(int i = 1; i < 1001; i++) {

            if(((i % 3) == 0) && ((i % 5) == 0)) {
                sum += i;
                cnt++;
                System.out.println(i + " is the value of i");

                if(cnt == 5) {
                    break;
                }
            }
        }
        System.out.println(sum + " is the sum.");

    }

    public static boolean isPrime(int n) {

        if(n == 1) {    //needs to be a whole number greater than 1
            return false;
        }

        //starts greater than 1
        //to find numbers that divide into n '/ 2' optimization, no number that is greater than n/2 is going to divide into n
        // optimized Math.sqrt(n)
        for(int i = 2; i <= (long) Math.sqrt(n); i++) {
            //checks if remainder is 0, a number divides evenly into the number passed in.
            if(n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static double calculateInterest(double amount, double interestRate) {
        return(amount * (interestRate / 100));
    }
}
