package com.udemy.junit;

import static org.junit.Assert.*;

public class BankAccountTest {

    @org.junit.Test // this annotation tells Junit that this is a test method
    public void deposit() {
        fail("This test hasn't been implemented");
    }

    @org.junit.Test
    public void withdraw() {
        fail("This test hasn't been implemented");
    }

    @org.junit.Test
    public void getBalance() {
        fail("This test hasn't been implemented");
    }
}
/*
* create a separate run configuration for all out tests because
* we want to test a class in our app and not actually run the
* app itself.
*
* Best practice to call fail() in test method stubs since they have
* not been implemented yet.
*
* We test the output of a method against an assertion that we've made
* about the expected output. and the assertion will fail if the
* test condition isn't met.
* */