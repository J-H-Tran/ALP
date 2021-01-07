package com.udemy.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BankAccountTestParameterized {

    private BankAccount account;
    // these values being assigned will come from the collection in testConditions() via constructor
    private double amount;
    private boolean branch;
    private double expected;

    public BankAccountTestParameterized(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;
    }

    @org.junit.Before
    public void setup() {
        account = new BankAccount("Jon", "Tran", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][]{
                {100.00, true, 1100.00},
                {200.00, true, 1200.00},
                {325.14, true, 1325.14},
                {489.33, true, 1489.33},
                {1000.00, true, 2000.00}
        });
    }

    @org.junit.Test
    public void deposit() {
        double balance = account.deposit(amount, branch);
        assertEquals(expected, balance, 0.01);
    }
}
/* How to tell JUnit that we want tu use specific parameters to test?
 * We annotate a method with @Parameterized.Parameters and have it return a Collection
 *
 * When we run the parameterized Test JUnit will create a new instance of the BankAccount
 * Test Parameterized class for each set of test data. It will use the class constructor
 * to set instance variables to the values we specified.
 *
 * We need to do 2 things: add instance variables for the deposit amount, branch value, expected value,
 * add a constructor that accepts the values and sets the instance variables.
 *
 * So this is how we can run multiple test conditions with a single Test method.
 * Though it may require a decent amount of set up it does cut down on the amount
 * of test code we might have to write and maintain.
 **/
