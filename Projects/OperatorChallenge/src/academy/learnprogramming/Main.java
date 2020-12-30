package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        double val1 = 20.00;
        double val2 = 80.00;

        val1 = (val1 + val2) * 100.00;
        val1 = val1 % 40.00;

        boolean isRemainder = (val1 == 0) ? true : false;

        System.out.println("boolean: " + isRemainder);

        if (!isRemainder) {
            System.out.println("Got some remainder");
        }
    }
}
