package academy.learnprogramming;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int myIntValue = 10;
        int anotherIntValue = myIntValue;

        System.out.println(myIntValue + ": is myIntValue");
        System.out.println(anotherIntValue + ": is anotherIntValue");

        anotherIntValue++;

        System.out.println(myIntValue + ": is myIntValue");
        System.out.println(anotherIntValue + ": is anotherIntValue");

        //'new' operator creates a new object in memory
        int[] myIntArray = new int[5];  //arrays are reference types; holds a reference to he object and not the object itself
        int[] anotherArray = myIntArray;

        System.out.println("myIntArrays = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

    }
}
