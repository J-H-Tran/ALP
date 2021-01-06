package com.udemy.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        new Thread(new CodeToRun()).start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from Runnable in main");
            }
        }).start();*/
        /*new Thread(() -> {
            System.out.println("Printing1 from Runnable lambda");
            System.out.println("Printing2 from Runnable lambda");
            System.out.println("Printing3 from Runnable lambda");
        }).start();*/

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        /*Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });*/

        /* lambda is being passed as a 2nd arg instead of an anonymous Comparator
         * one nice thing about lambdas is that it's easy to see which code will be executed
         * can be simplified even further. Compiler can infer the parameter types and we don't actually need to include
         * types in our arg list. Compiler can infer from the first arg that the objs to be compared are of type Employee
         **/
        Collections.sort(employees, (employee1, employee2) ->
                employee1.getName().compareTo(employee2.getName()));
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }

        /*String sillyStr = doStringOp(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, employees.get(0).getName(), employees.get(1).getName());*/

//        UpperConcat uc = (s1, s2) -> s1.toUpperCase() +" "+ s2.toUpperCase();
        UpperConcat uc = (s1, s2) -> {
            String result = s1.toUpperCase() +" "+ s2.toUpperCase();
            return result;
        };

        String sillyStr = doStringOp(uc, employees.get(0).getName(), employees.get(1).getName());

        System.out.println(sillyStr);
    }

    public final static String doStringOp(UpperConcat uc,  String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}
// class CodeToRun implements Runnable {}
/* Lambdas
 * introduced in Java 8, they provide us with an easier way to work with interfaces that
 * only have one method. Often used in places where we use an anonymous inner class
 *
 * Runnable review,
 *
 *         new Thread(new Runnable() {
 *           @Override
 *           public void run() {
 *               System.out.println("Printing from Runnable in main");
 *           }
 *       }).start();
 *
 * what we really care about here is the println() the rest of the code is just filler to just execute the print.
 * The rest of the code is there to instantiate an obj and implement a single method in Runnable Interface.
 * Would be nice if we could just pass the print statement to the thread constructor and not have to write all
 * the extra code that isn't really doing much. With lambdas we can actually accomplish this.
 *
 * Every lambda expression has 3 parts: argument list, arrow token, body
 *
 * Q. When compiler sees a lambda expression how does it know what to do?
 * A. It knows that one of the thread classes constructor's accepts a Runnable and it also knows that the Runnable
 *  Interface only has 1 method, run(), which takes no args. It's able to match the lambda expression argument list,
 *  no args, with the run(). Because the compiler needs to match the lambda expression to a method. Lambda expressions
 *  can only be used with interfaces that contain only one method that has to be implemented. These interfaces are
 *  also known as functional interfaces.
 *
 * By using a lambda expression, instead of creating a class that implements Runnable or using an anonymous class
 * we're able to reduce the lines of code we have to write and can focus on what we care about, the
 * code that we want to run.
 *
 * For Comparator we can use a lambda even though there are 2 methods we can implement because the equals()
 * will always have a default implementation. All classes descend from Object and Object contains equals() and
 * that means that every instance that implements Comparator will already have an implementation of equals().
 * Therefore, Comparator really only has a single method that always has to be implemented by classes that implement
 * the Comparator. For that reason it's a functional interface and we can use lambdas instead of an anonymous inner
 * class.
 *
 * We can assign lambdas to variables and use them later. If we want to use the same lambda in more than one place
 * we only need to define it once and we can use it wherever we need it. If lambda's body contains more than one
 * statement we need to include the return keyword. When we put {} to declare a body for the lambda we must use
 * the return keyword even if it only has a single statement.
 * */