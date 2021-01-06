package com.udemy.lambdas;

import java.util.ArrayList;
import java.util.List;

public class EmployeeeMain {

    public static void main(String[] args) {
        Employeee john = new Employeee("John Doe", 30);
        Employeee tim = new Employeee("Tim Buchalka", 21);
        Employeee jack = new Employeee("Jack Hill", 40);
        Employeee snow = new Employeee("Snow White", 22);
        Employeee red = new Employeee("Red Foreman", 35);
        Employeee jt = new Employeee("JT", 31);

        List<Employeee> employeees = new ArrayList<>();
        employeees.add(john);
        employeees.add(tim);
        employeees.add(jack);
        employeees.add(snow);
        employeees.add(red);
        employeees.add(jt);

        System.out.println("\nEmployees over 30:");
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
        });
        /*for (Employeee employee : employeees) {
            if (employee.getAge() > 30) {
                System.out.println(employee.getName());
            }
        }*/

        /*employeees.forEach(employeee -> {
            System.out.println(employeee.getName());
            System.out.println(employeee.getAge());
        });*/
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
 *  */