package com.udemy.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdaChallengeMain {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Something2 Something2 Something3 Something4 Something5";
                String[] parts = myString.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

        Runnable runnable1 = () -> {
            String myString = "Something2 Something2 Something3 Something4 Something5";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

        Function<String, String> lambdaFunc = (String s) -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };

        lambdaFunc.apply("1234657980");
        everSecChar(lambdaFunc, "1234567890");
        System.out.println(everSecChar(lambdaFunc, "1234567890"));

        Supplier<String> iLoveJava = () -> "I love Java!";
        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );
        List<String> firstUpperCaseList = new ArrayList<>();
        topNames2015.forEach(name ->
                firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
//        firstUpperCaseList.sort((s1, s2) -> s1.compareTo(s2));
//        firstUpperCaseList.forEach(s -> System.out.println(s));
        firstUpperCaseList.sort(String::compareTo);
        firstUpperCaseList.forEach(System.out::println);

        /*topNames2015
                .stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .sorted()
                .forEach(System.out::println);*/
        System.out.println("=========================================");
        long var = topNames2015
                .stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .peek(System.out::println)
                .filter(s -> s.startsWith("A"))
                .count();
        System.out.println(var);

        topNames2015
                .stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .peek(System.out::println)
                .filter(s -> s.startsWith("A"));
//                .collect(Collectors);

    }

    public static String everSecChar(Function<String, String> lambda, String source) {
        return lambda.apply(source);
    }
}
