package com.udemy.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> bingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6", "g64",
                "B53", "G49", "G60", "G50",
                "I26", "I17", "I29", "O71");

        List<String> gNums = new ArrayList<>();

        bingoNumbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) {
                gNums.add(number);
//                System.out.println(number);
            }
        });

        gNums.sort((String s1, String s2) -> s1.compareTo(s2));
        gNums.forEach((String s) -> System.out.println(s));

    }
}
/*Stream
* completely unrelated to IO streams.
* What we're referring to here is a sequence of computations
* A set of computational steps that are chained together.
* */