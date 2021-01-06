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
        UpperConcat2 uc = (s1, s2) -> {
            System.out.println("The lambda expression's class is: " + getClass().getSimpleName());
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
}
/*
 * A lambda expression ins't a class. When the code runs, an anonymous instance isn't created.
 * Instead, the lambda is treated like a nested block of code and it has the same scope as a nested block.
 * */
