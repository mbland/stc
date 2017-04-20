package com.schibsted.engprod.stc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void shouldReturnSumZeroForEmptyString() {
        StringCalculator sc = new StringCalculator();
        assertEquals(0, sc.add(""));
    }

    @Test
    public void shouldReturnSumForTwoPositiveNumbers() {
        StringCalculator sc = new StringCalculator();
        assertEquals(3, sc.add("1,2"));
    }

    @Test
    public void shouldReturnSumForAListOfPositiveNumbers() {
        StringCalculator sc = new StringCalculator();
        assertEquals(105, sc.add("1,2,3,99"));
    }

    @Test
    public void shouldReturnSumIfNewlineAsDelimiter() {
        StringCalculator sc = new StringCalculator();
        assertEquals(6, sc.add("1\n2,3"));
    }

    @Test
    public void shouldReturnSumIfCommaAndNewlineAsDelimiter() {
        StringCalculator sc = new StringCalculator();
        assertEquals(1, sc.add("1,\n"));
    }

    @Test
    public void shouldReturnSumWithSpecifiedDelimiter() {
        StringCalculator sc = new StringCalculator();
        assertEquals(3, sc.add("//;\n1;2"));
    }

    @Test
    public void shouldThrowExceptionIfANumberIsNegative() {
        StringCalculator sc = new StringCalculator();
        try {
            sc.add("1,-2");
            fail("Should've thrown exception by now");
        } catch (IllegalArgumentException iae) {
            assertEquals("Negatives not allowed", iae.getMessage());
        }
    }
}
