package com.udemy.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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

        /*employeees.forEach(employeee -> {
            String lastName = employeee.getName().substring(employeee.getName().indexOf(' ') + 1);
            System.out.println("Last name: " + lastName);
        });*/

        Function<Employeee, String> getLastName = (Employeee employeee) -> {
            return employeee.getName().substring(employeee.getName().indexOf(' ') + 1);
        };

        String lastName = getLastName.apply(employeees.get(1));
        System.out.println(lastName);

        Function<Employeee, String> getFirstName = (Employeee employeee) -> {
            return employeee.getName().substring(0, employeee.getName().indexOf(' ') + 1);
        };

        for (Employeee employeee : employeees) {
            if (random.nextBoolean()) {
                System.out.println(getAName(getFirstName, employeee));
            } else {
                System.out.println(getAName(getLastName, employeee));
            }
        }

        // chaining functions requires 2 steps: create a 'composed' function (the function that calls andThen())
        // which will run first, then the Function passed as arg in andThen() will operate on its result
        Function<Employeee, String> upperCase = (Employeee employeee) -> employeee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));

        Function chainedFunction = upperCase.andThen(firstName);

        System.out.println(chainedFunction.apply(employeees.get(0)));

        BiFunction<String, Employeee, String> concatAge = (String name, Employeee employeee) -> {
            return name.concat(" " + employeee.getAge());
        };

        String upperName = upperCase.apply(employeees.get(0));
        System.out.println(concatAge.apply(upperName, employeees.get(0)));

        IntUnaryOperator incByFive = i -> i + 5;
        System.out.println(incByFive.applyAsInt(10));
    }

    private static String getAName(Function<Employeee, String> getName, Employeee employeee) {
        return getName.apply(employeee);
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
/*Consumer Interface
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
 *
 * In a more complex app there may be cases where 30 lines of code execute but we only want 2 lines of code to run
 * in specific cases. Rather than using interfaces with methods that are called at the appropriate points or writing
 * different methods for every situation we can actually pass in functions for the parts that vary. That way the code
 * will be more concise and easy to follow.
 * Useful in callbacks... Let's say we're fetching data in a background thread and when data is ready we want to
 * send a message out in some way on the background thread and the specifics of what we want to do can vary.
 * So we want to be able to specify what code to run when the data has been fetched.
 * Java provides ways to notify threads and run code when a background tasks finishes but we can also use Function.
 *
 * We can eliminate the need to write an interface and multiple classes that then implement that interface by
 * creating a Function for each algorithm we may want to use. lambdas reduce the amount of code we need to write
 * and can make code easier to follow. Rather than having a bunch of classes each with a resizeImage() we can define
 * all the Functions in one place and use them anytime we need to use a callback. When we want to run code when a
 * specific non-user interface even occurs. Using a Function is one option to consider.
 *
 * The power and convenience of using lambda expressions is the capability to execute specific code when we want
 * to at specific times.
 * --------------------------------------------------------------------------------------------------------------------
 * When you chain functions together, the return value of each function is operated on by the next
 * ie. apply().andThen().andThen(), return value from apply gets operated on by andThen(), and so on.
 * There's also compose() that runs backwards
 *
 * BiFunction: can take 2 args and returns a value. no compose() for reverse chaining
 * Unary: takes 1 arg and returns same type as arg
 *  */