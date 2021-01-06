package com.udemy.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> bingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6", "g64",
                "B53", "G49", "G60", "G50",
                "I26", "I17", "I29",
                "O71");

//        List<String> gNums = new ArrayList<>();

        /*bingoNumbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) {
                gNums.add(number);
//                System.out.println(number);
            }
        });

        gNums.sort((String s1, String s2) -> s1.compareTo(s2));
        gNums.forEach((String s) -> System.out.println(s));*/

        bingoNumbers
                // returns, a stream that contains all the items in the list in the same order they originally occurred. references
                .stream()
                // returns String from toUpperCase(). accepts a Function which takes single arg, passed in String.toUpperCase() has no args but has source String.
                .map(String::toUpperCase)
                // returns only items that returned true from Predicate. Takes a Predicate and not a Function, passed in lambda takes a single arg and returns a boolean
                .filter(s -> s.startsWith("G"))
                // sorts based on natural ordering, can take a Comparator if needed
                .sorted()
                // takes a consumer, accepts an arg and doesn't return a value. Since no value is returned chain ends here nothing to pass on to a next step
                .forEach(System.out::println); // not the same forEach() from Iterable Interface but does the same thing

        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71"); // any type of objects but not mixed types
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream); // concat() is static so can't use in a stream but can use as a source for a stream
//        System.out.println(concatStream.count());
        System.out.println("=============================================================");
        // distinct() uses equals() for comparison to check for dupes. Since our stream contains Strings it will use String.equals() for comparisons
//        System.out.println(concatStream.distinct().count());
        System.out.println(concatStream
                .distinct()
                .peek(System.out::println) // mainly exists for debugging
                .count());

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        Department hr = new Department("Human Resources");
        Department ac = new Department("Accounting");

        hr.addEmployee(tim);
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        ac.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(ac);

        departments.stream()
                // flatMap() wants a function that returns a Stream. Each department in the source stream becomes the argument to the function
                // for every department we call getEmployees(), which returns a list, then stream() returns a Stream of employees
                // flattens nested lists
                .flatMap(department -> department.getEmployees().stream()) // returns Stream with every Employee from each Department
                .forEach(System.out::println);

        /*List<String> sortedGNumbers = bingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());*/ // we now have List that we can continue to work with if we wanted to

        List<String> sortedGNumbers = bingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted() // there are 2 versions of collect(). 2nd version takes 3 args: Supplier, BiConsumerAccumulator, BiConsumerCombiner
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (String s : sortedGNumbers) {
            System.out.println(s);
        }

        Map<Integer, List<Employee>> groupedByAge = departments
                .stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                // this reduce() accepts a BiFunction, returns a result
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                // we use ifPresent() to print because that version of reduce() is a terminal op
                .ifPresent(System.out::println); // not part of the Stream, non-Stream method called on Stream chain result.

        Stream.of("ABC", "AC", "BAA", "CCCC", "EEFF")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
        .count();
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
 *
 * map() - It's mapping each item in the input stream to the results returned by the function passed in to it's
 * argument. Each resulting toUpperCase value returned from the method is mapped to each item in the stream from stream().
 *
 * Stream.forEach() - terminal operation, returns void or non-Stream forces and end in the chain
 * intermediate operations return a Stream
 *
 * Stream Pipeline: Source, zero or more intermediate ops, terminal op
 *
 * flatMap() - map a single obj to more than one obj
 * The one to use when you want to perform operations on a List that isn't the source. So an obj containing the List
 * as a source.
 *
 * collect() - terminal op, collects elements in stream into a different type of result. To create a reduced list.
 * There are many APIs that we can map to the Supplier, Accumulator, and Combiner. This allows us to do all kinds of
 * things using collect. ie. if we have a lot of employees and there are groups of employees with the same age.
 * We can create a map of Lists based on age using Collectors.groupingBy()
 *
 * reduce() - combines all the items in a Stream into a single result. 3 versions.
 *
 * Notes:
 * - Streams cannot be reused. If we've called a terminal op on a stream we'll receive the IllegalStateException if we
 *  try to operate on that Stream again
 * - Are lazily evaluated, intermediate ops are not performed until there's a terminal op
 * - We can use more type specific Stream like Int, Long, Double and they have additional methods like sum(), min(), max()
 * - Parallel Streams when we want to increase performance by executing streams in parallel. Collection.parallelStream()
 * */