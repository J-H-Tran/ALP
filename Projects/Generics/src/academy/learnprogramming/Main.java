package academy.learnprogramming;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //ArrayList items = new ArrayList(); //ArrayList used here is raw type, *never use
        ArrayList<Integer> items = new ArrayList<>(); //parameterized type, with the angled brackets
        items.add(1);
        items.add(2);
        items.add(3);
//        items.add("tim"); //throws an error, can't be casted to Integer. Runtime error
        items.add(4);
        items.add(5);

        printDoubled(items);
    }

    private static void printDoubled(ArrayList<Integer> n) {
        for (Object i : n) {
            System.out.println((Integer) i * 2);
        }
    }
}
