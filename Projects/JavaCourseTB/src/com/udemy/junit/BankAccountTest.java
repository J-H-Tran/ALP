package com.udemy.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BankAccountTest {

    private BankAccount account;
    private static int cnt;

    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("This executes before any test methods run. Count = " + cnt++);
    }

    @org.junit.Before // method annotated with Before is run before every single Test method
    public void setup() {
        account = new BankAccount("Jon", "Tran", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test");
    }

    @org.junit.Test // this annotation tells Junit that this is a test method
    public void deposit() {
        double balance = account.deposit(200.00, true);
        // 3rd arg: delta, to specify leeway in comparison. As long as diff between expected and actual is within Delta assertion will Pass
        assertEquals(1200.00, balance, 0); // when comparing obj, uses equals() to make comparison
    }

    @org.junit.Test
    public void withdraw_branch() {
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void withdraw_notBranch() {
        account.withdraw(600.00, false);
        fail("Should've thrown an IllegalArgumentException");
    }

    @org.junit.Test
    public void getBalance_deposit() {
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void getBalance_withdraw() {
        double balance = account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void isChecking_true() {
        assertTrue("Account is NOT a CHECKING account", account.isChecking()); // message when fails
    }

    @org.junit.AfterClass
    public static void afterClass() {
        System.out.println("This executes after every test method runs Count = " + cnt++);
    }

    @org.junit.After // runs after every single Test method
    public void tearDown() {
        System.out.println("Count = " + cnt++);
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
 *
 * assertNotEquals()
 * assertArrayEquals(), assertEquals() won't work on arrays unless it's the same array instance
 * assertNull() assertNotNull()
 * assertSame() assertNotSame() - use when checking whether 2 instances are the exact same instance. compares obj refs
 * assertThat() - compares actual value agains a matcher (JUnit 4.4+ Matcher class)
 * ^more powerful than the other assert methods since we can compare the actual value against a range of values
 *
 * What if we only want to initialize setup once instead of every time a test method runs?
 * @org.junit.BeforeClass can do that. We'll need @org.junit.AfterClass to run any clean up code needed
 * Before - runs before any test methods
 * After - runs after all test methods executed
 * Must be declared public static void name() {}
 *
 * With the print statements in the BeforeClass and AfterClass, possible to not see BeforeClass statement printed
 * first since all the statements are spooled to an IO thread. May appear out of order when printing.
 *
 * Parameterized Tests
 * So we want every test to start 'fresh' and that can result in a lot of repetitive code.
 * ie. trying to deposit 5 diff amounts and verifying the resulting balance -> 5 separate Test methods
 * Or we can just write a parameterized test. Requires a class annotation
 * */