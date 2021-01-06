package com.udemy.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaScopeMain {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething();
        System.out.println(s);
        anotherClass.printValue();
    }

    public final static String doStringOp(UpperConcat2 uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

class Employee2 {
    private String name;
    private int age;

    public Employee2(String name, int age) {
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

interface UpperConcat2 {
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass {
    public String doSomething() {
        int i = 0;
//        i++;

        UpperConcat2 uc = (s1, s2) -> {
            System.out.println("The lambda expression's class is: " + getClass().getSimpleName());
            System.out.println("i in the lambda expression: " + i); // local variables must be effectively final for anon class & lambdas to use
            String result = s1.toUpperCase() + " " + s2.toUpperCase();
            return result;
        };

        System.out.println("The AnotherClass's name is: " + getClass().getSimpleName());
        return LambdaScopeMain.doStringOp(uc, "String 1", "String 2");
    }
    /*public String doSomething() {
        System.out.println("The AnotherClass's name is: " + getClass().getSimpleName());
        return Main.doStringOp(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The anon class's name is: " + getClass().getSimpleName());
                return s1.toUpperCase() + " " + s2.toUpperCase();
            }
        }, "String 1", "String 2");
    }*/
    public void printValue() {
        int number = 25;
        Runnable runnable = () -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The value is: " + number);
        };
        new Thread(runnable).start();
    }
}
/*
 * A lambda expression ins't a class. When the code runs, an anonymous instance isn't created.
 * Instead, the lambda is treated like a nested block of code and it has the same scope as a nested block.
 *
 * Q. Why do local variables have to be declared as final when we use them within an anon class?
 * Because the local variable doesn't belong to the anon class instance. Behind the scenes, the variable is replaced
 * by whatever the value of i is when the instance is constructed so it's possible we may not use the instance
 * of the anon class for a while. We may even pass it to a method in another class and there'd be no way for
 * the java runtime to update the value within the anon class instance every time it changed. Values would get out of
 * sync, so the values of local variables declared outside the scope of the anon class are not allowed to be changed
 * and have to be declared final.
 *
 * Lambda expressions are treated like nested blocks.
 * Because lambdas may not be immediately evaluated, any variables that we use within them from outside the lambda
 * must be final. We can safely change the variables declared within the lambda because they are within scope and thus,
 * can't be changed from outside.
 * */
