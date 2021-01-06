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
                "I26", "I17", "I29",
                "O71");

        List<String> gNums = new ArrayList<>();

        /*bingoNumbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) {
                gNums.add(number);
//                System.out.println(number);
            }
        });

        gNums.sort((String s1, String s2) -> s1.compareTo(s2));
        gNums.forEach((String s) -> System.out.println(s));*/

        bingoNumbers
                .stream() // returns, a stream that contains all the items in the list in the same order they originally occurred
                .map(String::toUpperCase) // returns String from toUpperCase(). accepts a Function which takes single arg, passed in String.toUpperCase() has no args but has source String.
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

    }
}
/*Stream - set of obj references, ordering of these references matches ordering of the collection
 * completely unrelated to IO streams.
 * What we're referring to here is a sequence of computations
 * A set of computational steps that are chained together.
 *
 * When we want to use a stream that uses a collection as its source, the stream() will always be the first call we make.
 * 2 requirements:
 * - Non-Interfering, these computations don't change the original stream in any way
 * - Must be stateless, the result of an operation can't depend on any state outside of the operation.
 *   ie. it cannot depend on variable values in a previous step. each operation should be seen as an independent step
 *  that's operating on a stream argument.
 *
 * '::' Requirement: when args list of lambda expression matches function execution args list in lambda body
 * If all we are doing is executing a method in our lambda expression we can use a method reference in place of it.
 * With method references 'ClassName::methodName()' that takes a parameter the compiler has to be able to infer what the arg is.
 * compiler has no way of knowing we want "G" passed into startsWith() so we can't use a method reference there.
 * */