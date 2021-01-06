package com.udemy.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EmployeeeMain {

    public static void main(String[] args) {
        Employeee john = new Employeee("John Doe", 30);
        Employeee tim = new Employeee("Tim Buchalka", 21);
        Employeee jack = new Employeee("Jack Hill", 40);
        Employeee snow = new Employeee("Snow White", 22);
        Employeee red = new Employeee("Red Foreman", 35);
        Employeee jt = new Employeee("J. T.", 31);

        List<Employeee> employeees = new ArrayList<>();
        employeees.add(john);
        employeees.add(tim);
        employeees.add(jack);
        employeees.add(snow);
        employeees.add(red);
        employeees.add(jt);

        /*System.out.println("\nEmployees over 30:");
        employeees.forEach(employeee -> {
            if (employeee.getAge() > 30) {
                System.out.println(employeee.getName());
            }
        });
        System.out.println("\nEmployees 30 and under:");
        employeees.forEach(employeee -> {
            if (employeee.getAge() <= 30) {
                System.out.println(employeee.getName());
            }
        });*/
        /*for (Employeee employee : employeees) {
            if (employee.getAge() > 30) {
                System.out.println(employee.getName());
            }
        }*/
        printEmployeesByAge(employeees, "Employee over 30.", employeee -> employeee.getAge() > 30);
        printEmployeesByAge(employeees, "Employee 30 and under.", employeee -> employeee.getAge() <= 30);

        IntPredicate greaterThanFifteen = i -> i > 15;
        IntPredicate lessThanHundred = (i) -> i < 100;
        System.out.println(greaterThanFifteen.test(10));
        int a = 15;
        System.out.println(greaterThanFifteen.test(a + 1));

        System.out.println(greaterThanFifteen.and(lessThanHundred).test(50));
        System.out.println(greaterThanFifteen.and(lessThanHundred).test(500));

        Random random = new Random();
        Supplier<Integer> randSupplier = () -> random.nextInt(1000);
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(randSupplier.get());
        }
        /*for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000));
        }*/

        employeees.forEach(employeee -> {
            String lastName = employeee.getName().substring(employeee.getName().indexOf(' ') + 1);
            System.out.println("Last name: " + lastName);
        });

        Function<Employeee, String> getLastName = (Employeee employeee) -> {
            return employeee.getName().substring(employeee.getName().indexOf(' ') + 1);
        };

        String lastName = getLastName.apply(employeees.get(1));
        System.out.println(lastName);
    }

    private static void printEmployeesByAge(List<Employeee> employeees,
                                            String ageText,
                                            Predicate<Employeee> ageCondition) {
        System.out.println("\n" + ageText);
        for (Employeee employeee : employeees) {
            if (ageCondition.test(employeee)) {
                System.out.println(employeee.getName());
            }
        }
    }
}
/*
 * We pass forEach() a lambda expression that meets the requirements of the Consumer Interface
 * It accepts 1 arg and performs an action that doesn't return a value. ie. the lambda below
 * It's called a Consumer because it's 'obj in, nothing out' OINO.
 * This is perfect for iterating a list and printing it's contents.
 *
 * Each iteration of forEach() calls Consumer's accept() and we pass it employeee obj as arg.
 * accept() evaluates the lambda expression using the passed employeee obj as the arg, does the print, and
 * nothing is returned.
 * The employeee is 'consumed' by the forEach() and the iterator moves to the next employeee obj.
 *
 * - Iterator calls Consumer.accept() on each obj in arg list
 * - accept() evaluates lambda body and methods called on obj from arg list
 *
 * ultimately, it's the consumer which was passed as a lambda expression that does the work.
 * --------------------------------------------------------------------------------------------------------------------
 * Predicate:
 * Interface built into java.util.function package
 * test() - a functional method that accepts a Predicate that returns a boolean
 * We can replace the if condition we're using in the 2 forEach() with a predicate.
 *
 * Predicate args are just lambda expressions that match the Predicate Interface.
 * they accept 1 arg and return a boolean. This is quite powerful because we can pass all kinds of conditions
 * to the method and we don't have to write a different if condition to filter the list based on our criteria.
 * All we need is a new lambda expression that can map to the Predicate Interface.
 *
 * In general, Consumer & Predicate Interfaces don't care about the type of args being passed to the lambda expression
 * They use Generics to infer the type. There are though, Consumers and Predicates that expect a specific type.
 * double Consumer, int Consumer, long Consumer; similarly with Predicate. Where possible, it's better to use
 * the more specific type of lambda to improve readability.
 * --------------------------------------------------------------------------------------------------------------------
 * Supplier Interface:
 * Doesn't accept any args but returns a value
 * Because Supplier always returns a value we have to include the expected return type when we declare it.
 * We can pass suppliers to methods.
 * Examples:
 * Sometimes you want to supply an obj of type A and other times an obj of type B, where obj B is a subclass of A.
 * Perhaps we may have a case for where a supplier is providing an IO Stream and used as a counter.
 * Can be used to test an app
 * A supplier could instantiate objs and perhaps populate them with random values. These also have specifics types
 * like Predicates do.
 * --------------------------------------------------------------------------------------------------------------------
 * Function Interface:
 * Previously, Consumers don't return a value, Predicate only returns a boolean, Supplier doesn't take any args!
 * But, Function takes one arg and returns a value. apply()
 * We could've created a regular method called getLastName() or use an anon inner class but, by using Function,
 * we can pass code that accepts and returns a value to a method in the form of a lambda expression and then run
 * the code without having to create an interface and a class that implements the interface. We can change what a
 * method does based on the function we pass in.
 *  */