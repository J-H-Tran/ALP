package com.udemy.junit;

import static org.junit.Assert.*;

public class BankAccountTest {

    @org.junit.Test // this annotation tells Junit that this is a test method
    public void deposit() {
        BankAccount account = new BankAccount("Jon", "Tran",1000.00, BankAccount.CHECKING);
        double balance = account.deposit(200.00,true);
        // 3rd arg: delta, to specify leeway in comparison. As long as diff between expected and actual is within Delta assertion will Pass
        assertEquals(1200.00, balance, 0); // when comparing obj, uses equals() to make comparison
    }

    @org.junit.Test
    public void withdraw() {
        fail("This test hasn't been implemented");
    }

    @org.junit.Test
    public void getBalance_deposit() {
        BankAccount account = new BankAccount("Jon", "Tran",1000.00, BankAccount.CHECKING);
        double balance = account.deposit(200.00,true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void getBalance_withdraw() {
        BankAccount account = new BankAccount("Jon", "Tran",1000.00, BankAccount.CHECKING);
        double balance = account.withdraw(200.00,true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void isChecking_true() {
        BankAccount account = new BankAccount("Jon", "Tran",1000.00, BankAccount.CHECKING);
        assertTrue("Account is NOT a CHECKING account",account.isChecking()); // message when fails
    }

    /*@org.junit.Test
    public void dummyTest() {
        assertEquals(20,21); // 1st arg is expected result, 2nd is value we're testing. Pass if they're equal
    }*/
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
* ------------------------------------------------------------------------
* Test methods requirements:
* - must be annotated with @org.junit.Test
* - public access modifier
* - void return type
* - can the test run and pass on it's own? Answer should always be Yes.
* Assertion
*
* Test methods should be self-contained. Whatever happens in one test method
* shouldn't depend on what happens in another test method.
* We can have instance variables in our test class though.
* Depending on the code it may make sense to create a single instance of a class
* that we're testing so all the test methods can use when writing tests.
* Not a best practice for a test method to have multiple assertions.
* usually 1 per test method.
* A good practice: a test method name should give some indication of the actual
* condition they are testing.
*
* JUnit tests can be run as part of the build process instead of manually.
* Then the results can be saved to a file where someone can look at the results
* so messages can be really useful there
* */