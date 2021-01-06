package com.udemy.junit;

public class Main {

    public static void main(String[] args) {
        System.out.println("Application is running?");
    }
}
/*Testing: first line of defense, when testing code, is called unit testing
 * Typically performed by the developer/team. unit typically refers to a method.
 *
 * Rather than test manually we can use a unit testing framework to write tests
 * that will run in an automated fashion.
 *
 * The build process for an app will typically run a unit test suite after each
 * successful build. If tests don't succeed 100% then we can tell what recent
 * changes to the code may have broken what part of the app
 * */