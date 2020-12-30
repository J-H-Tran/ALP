package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
        // byte
        // short
        // int
        // long
        // float
        // double
        // char
        // boolean
        String myString = "This is a string";
        System.out.println("myString: " + myString);
        myString = myString + ", this is more.";
        System.out.println("myString: " + myString);
        myString = myString + " \u00A9 2020";
        System.out.println("myString: " + myString);

        String numberString = "250.55";
        numberString = numberString + "49.95";
        System.out.println(numberString);

        String lastString = "10";
        int myInt  = 50;
        lastString = lastString + myInt;
        System.out.println("lastString: " + lastString);
    }
}
