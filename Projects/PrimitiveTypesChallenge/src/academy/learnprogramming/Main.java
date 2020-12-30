package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        byte byteValue = 100;
        short shortValue = 30000;
        int intValue = 27;
        long longValue = (50000 + (10 * (byteValue + shortValue + intValue)));
        System.out.println("longVal: " + longValue);

        short shortTotal = (short)(50000 + (10 * (byteValue + shortValue + intValue))); //type cast in front of expressions
    }
}
