package com.udemy.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JUnitChallengeTestParameterized {

    private JUnitChallenge utilities;

    private String testStr;
    private String resultStr;

    public JUnitChallengeTestParameterized(String test, String result) {
        this.testStr = test;
        this.resultStr = result;
    }

    @org.junit.Before
    public void setup() {
        utilities = new JUnitChallenge();
    }

    @Parameterized.Parameters
    public static Collection<Object> testConditions() {
        return Arrays.asList(new Object[][]{
                {"ABCDEFF", "ABCDEF"}, {"AB88EFFG", "AB8EFG"},
                {"112233445566", "123456"}, {"ZYZQQB", "ZYZQB"},
                {"A", "A"}
        });

    }

    @org.junit.Test
    public void removePairs() {
        String actual = utilities.removePairs(testStr);
        assertEquals(resultStr, actual);
    }
}
