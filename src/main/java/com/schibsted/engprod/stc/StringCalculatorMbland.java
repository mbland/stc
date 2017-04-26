package com.schibsted.engprod.stc;

public class StringCalculatorMbland {
  public int add(String numbers) {
    int result = 0;
    int current = 0;
    String delimiter = "[\n,]";
    String[] parts;

    if (numbers.isEmpty()) {
      return result;
    } else if (numbers.startsWith("//")) {
      parts = numbers.split("\n");
      delimiter = parts[0].substring(2);

      if (parts.length != 2) {
        return 0;
      }
      numbers = parts[1];
    }

    for (String number : numbers.split(delimiter)) {
      current = Integer.parseInt(number);
      if (current < 0) {
        throw new NumberFormatException("Negatives not allowed: " + current);
      }
      result += current;
    }
    return result;
  }
}
