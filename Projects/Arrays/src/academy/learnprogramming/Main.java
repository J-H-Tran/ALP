package academy.learnprogramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        int[] myArray;  //declares array
//        myArray = new int[10];  //initializes array
        //int[] myArray = new int[10];

        int[] myInts = getIntegers(5);

        System.out.println("Array in descending order: ");
        printArray(sortDescending(myInts));
    }

    private static Scanner scanner = new Scanner(System.in);

    public static int[] getIntegers(int number) {

        String intList = "";
        int[] values = new int[number];

        System.out.println("Enter " + number + " integers.");

        for(int i = 0; i < values.length; i++) {
            values[i] = scanner.nextInt();
            intList += values[i] + " ";
        }

        System.out.println("Your array is: {" + intList + "}");
        return values;
    }

    public static List<Integer> sortDescending(int[] arr) {

        return Arrays
                .stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static void printArray(List<Integer> list) {

        list.forEach(x -> System.out.print(x + " "));
    }
}
