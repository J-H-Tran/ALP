package com.udemy.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JUnitChallengeTest {

    private JUnitChallenge utilities;
    // BeforeClass happens just once
    @org.junit.Before // so that every Test methods has a 'fresh' instance of the utilities class we use Before, which executes once for every Test method
    public void setup() {
        utilities = new JUnitChallenge();
        System.out.println("Running a test...");
    }

    @org.junit.Test
    public void everyNthCharTest() {
        char[] output = utilities.everyNthChar(new char[]{'h', 'e', 'l', 'l', 'o'}, 2);
        assertArrayEquals(new char[]{'e', 'l'}, output);
    }

    @org.junit.Test
    public void everyNthCharTest2() {
        char[] output = utilities.everyNthChar(new char[]{'h', 'e', 'l', 'l', 'o'}, 8);
        assertArrayEquals(new char[]{'h', 'e', 'l', 'l', 'o'}, output);
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
        int valueToTest = utilities.converter(10, 5);
        assertEquals(300, valueToTest);
    }

    @org.junit.Test(expected = ArithmeticException.class)
    public void converter2() throws Exception {
        utilities.converter(10, 0);
    }

    @org.junit.Test
    public void nullIfOddLength() {
        assertNull("Null not received", utilities.nullIfOddLength("111"));
        assertNotNull(utilities.nullIfOddLength("11"));
    }
}
