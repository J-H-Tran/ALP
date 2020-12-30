package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        int result = 1 + 2; // 1 + 2 = 3
        int previousResult = result;
        System.out.println("previousResult: " + previousResult);

        result = result - 1; // 3 - 1 = 2
        System.out.println(result);
        System.out.println("previousResult: " + previousResult);

        result = result * 10; // 2 * 10 = 20
        System.out.println(result);

        result = result / 5; // 20 / 5 = 4
        System.out.println(result);

        result = result % 3; // remainder of (4 % 3) = 1
        System.out.println(result);

        result++; // 1 + 1
        System.out.println(result);

        result--; // 2 - 1
        System.out.println(result);

        result += 2; // 1 + 2
        System.out.println(result);

        result *= 10; // 3 * 10
        System.out.println(result);

        boolean isAlien = false;
        if (isAlien == false) {
            System.out.println("it is not an Alien");
        }

        boolean isCar = false;
        //Ternary Operator
        boolean wasCar = isCar ? true : false;
        if(wasCar) {
            System.out.println("wasCar is true");
        }
    }
}
