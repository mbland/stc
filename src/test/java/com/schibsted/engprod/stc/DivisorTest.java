package com.schibsted.engprod.stc;

import static org.junit.Assert.fail;

import org.junit.Test;

public class DivisorTest {

    /* Run this test a number of times and you'll get different results. Flaky! Can you fix it? */
    @Test
    public void shouldDivideAllCorrectly() {
        Divisor divisor = new Divisor();
        for (int i = 0; i < 100; i++) {
            double x = Math.floor(Math.random() * 10);
            double y = Math.floor(Math.random() * 10);
            Double expected = new Double(x / y);

            if (expected.compareTo(divisor.div(x, y)) != 0) {
                fail("X: " + x + " Y: " + y);
            }
        }
    }
}
