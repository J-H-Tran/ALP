package com.udemy.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JUnitChallengeTest {

    private static JUnitChallenge utilities;

    @org.junit.BeforeClass
    public static void setup() {
        utilities = new JUnitChallenge();
    }

    @org.junit.Test
    public void everyNthCharTest() {
        fail("Test method not implemented yet.");
    }

    @org.junit.Test
    public void removePairs() {
        String actual = utilities.removePairs("AABCDDEFF");
        assertEquals("ABCDEF", actual);
    }

    @org.junit.Test
    public void removePairs2() {
        String actual = utilities.removePairs("ABCCABDEEF");
        assertEquals("ABCABDEF", actual);
    }

    @org.junit.Test
    public void converter() {
        fail("Test method not implemented yet.");
    }

    @org.junit.Test
    public void nullIfOddLength() {
        fail("Test method not implemented yet.");
    }
}
