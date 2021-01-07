package com.udemy.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JUnitChallengeTest {

    private static JUnitChallenge utilities;

    @org.junit.BeforeClass
    public static void setup() {
        utilities = new JUnitChallenge();
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

    @org.junit.Test
    public void nullIfOddLength() {
        assertNull("Null not received", utilities.nullIfOddLength("111"));
        assertNotNull(utilities.nullIfOddLength("11"));
    }
}
