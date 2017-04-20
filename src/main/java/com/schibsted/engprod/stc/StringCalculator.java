package com.schibsted.engprod.stc;

import java.util.Arrays;

public class StringCalculator {

    private static final int ZERO_SUM = 0;

    public int add(final String numbers) {
        final String delimiter =
            numbers.startsWith("//") ? numbers.substring(2, numbers.indexOf("\n")) : ",";
        final String nums =
            numbers.replace("\n", delimiter).replace("//", "");
        try {
            return Arrays.stream(nums.split(delimiter))
                .filter(n -> !"".equals(n))
                .mapToInt(Integer::parseInt)
                .filter(n -> {
                    if (n < 0) throw new IllegalArgumentException("Negatives not allowed");
                    return true;
                })
                .sum();
        } catch (NumberFormatException nfe) {
            return ZERO_SUM;
        }
    }
}
