# TDD exercise

## About
This is an exercise in Test Driven Development. After the exercise there’s a walk-through of one
possible solution paths.

## Exercise string calculator
Requirements usually comes as increments. You start out with a set of features, then new features
get added. To understand how software evolves, it’s recommended that you follow the steps below one
by one. There’s a walkthrough of the exercise you may skip to, if you’d rather take the back seat.

### Implement a string calculator using TDD

#### Create a simple string calculator with a method int add(String numbers)
1. The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
    1. Start with the simplest test case of an empty string and move to 1 and two numbers
    1. Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
    1. Remember to refactor after each passing test
1. Allow the add method to handle an unknown amount of numbers
1. Allow the add method to handle new lines between numbers (instead of commas). The following input is ok: “1\n2,3” (will equal 6)
1. Support different delimiters
    1. To change a delimiter, the beginning of the string will contain a separate line that looks like this: “//[delimiter]\n[numbers…]”. For example “//;\n1;2” should return three where the default delimiter is “;”
    1. The first line is optional. All existing scenarios should still be supported
1. Calling add with a negative number will throw an exception “negatives not allowed”

## Walk-through TDD string calculator
This walkthrough follows the step above in the exercise, adding and changing code for each step.
Please note that this solution path may not be the best solution, and it’s definitely not the only
solution. It’s just an example how code can evolve using the quick feedback loop of writing the
test, implementing, refactoring and then start over.

### 1: Create the class and method

```
package com.schibsted.engprod.stc;

public class StringCalculator {

    public int add(final String numbers) {
        return 1;
    }
}
```

### 1: Create first test for simplest case

```
package com.schibsted.engprod.stc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void shouldReturnSumZeroForEmptyString() {
        StringCalculator sc = new StringCalculator();
        assertEquals(0, sc.add(""));
    }
}
```

### 1: Add implementation to satisfy simplest test

```
package com.schibsted.engprod.stc;

public class StringCalculator {

    public int add(final String numbers) {
        int sum = 0;
        try {
            sum = Integer.parseInt(numbers);
        } catch (NumberFormatException nfe) {
            // Do nothing.
        }
        return sum;
    }
}
```

### 1: Add test for next requirement

```
    @Test
    public void shouldReturnSumForTwoPositiveNumbers() {
        StringCalculator sc = new StringCalculator();
        assertEquals(3, sc.add("1,2"));
    }
```

### 1: Change implementation to satisfy latest test

```
package com.schibsted.engprod.stc;

import java.util.Arrays;

public class StringCalculator {

    public int add(final String numbers) {
        int sum = 0;
        try {
            sum = Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).sum();
        } catch (NumberFormatException nfe) {
            // Do nothing.
        }
        return sum;
    }
}
```

### 2: Add test for next requirement

```
    @Test
    public void shouldReturnSumForAListOfPositiveNumbers() {
        StringCalculator sc = new StringCalculator();
        assertEquals(105, sc.add("1,2,3,99"));
    }
```

### 2: Implementation for latest requirement

Looks like the latest implementation satisfies the new requirement!

### 3: Add test for new requirement to accept newline as delimiter

```
    @Test
    public void shouldReturnSumIfNewlineAsDelimiter() {
        StringCalculator sc = new StringCalculator();
        assertEquals(6, sc.add("1\n2,3"));
    }
```

### 3: Change implementation to satisfy new requirement

```
package com.schibsted.engprod.stc;

import java.util.Arrays;

public class StringCalculator {

    public int add(final String numbers) {
        int sum = 0;
        final String nums = numbers.replace("\n", ",");
        try {
            sum = Arrays.stream(nums.split(",")).mapToInt(Integer::parseInt).sum();
        } catch (NumberFormatException nfe) {
            // Do nothing.
        }
        return sum;
    }
}
```

### 3: Maybe just add a test for a corner case

```
    @Test
    public void shouldReturnSumIfCommaAndNewlineAsDelimiter() {
        StringCalculator sc = new StringCalculator();
        assertEquals(1, sc.add("1,\n"));
    }
```

### 3: Implementation for corner case

Looks like the latest implementation satisfies the corner case!

### 4: Add test for new requirement about specifying delimiter
`
```
    @Test
    public void shouldReturnSumWithSpecifiedDelimiter() {
        StringCalculator sc = new StringCalculator();
        assertEquals(3, sc.add("//;\n1;2"));
    }
```

### 4: Change implementation to satisfy latest test

```
package com.schibsted.engprod.stc;

import java.util.Arrays;

public class StringCalculator {

    public int add(final String numbers) {
        int sum = 0;
        final String delimiter = numbers.startsWith("//") ? numbers.substring(2, numbers.indexOf("\n")) : ",";
        final String nums = numbers.replace("\n", delimiter).replace("//", "");
        try {
            return Arrays.stream(nums.split(delimiter))
                .filter(n -> !"".equals(n))
                .mapToInt(Integer::parseInt)
                .sum();
        } catch (NumberFormatException nfe) {
            return sum;
        }
    }
}
```

### 5: Add test for new requirement about no negatives

```
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
```

### 5: Change implementation to satisfy latest test

```
package com.schibsted.engprod.stc;

import java.util.Arrays;

public class StringCalculator {

    public int add(final String numbers) {
    	int sum = 0;
        final String delimiter =
            numbers.startsWith("//") ? numbers.substring(2, numbers.indexOf("\n")) : ",";
        final String nums =
            numbers.replace("\n", delimiter).replace("//", "");
        try {
            return Arrays.stream(nums.split(delimiter))
                .filter(n -> !"".equals(n))
                .filter(n -> {
                    if (n.contains("-")) {
                        throw new IllegalArgumentException("Negatives not allowed");
                    }
                    return true;
                })
                .mapToInt(Integer::parseInt)
                .sum();
        } catch (NumberFormatException nfe) {
            return sum;
        }
    }
}
```

### Final refactor of the solution and run tests again

```
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
```

The final result is available in the classes StringCalculator and StringCalculatorTest in this repo.
