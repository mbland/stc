package com.schibsted.engprod.stc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringCalculatorMblandTest {
  private final StringCalculatorMbland calc = new StringCalculatorMbland();

  @Test
  public void emptyStringReturnsZero() {
    assertEquals(0, calc.add(""));
  }

  @Test
  public void singleNumberReturnsTheNumber() {
    assertEquals(1, calc.add("1"));
  }

  @Test
  public void addTwoNumbers() {
    assertEquals(3, calc.add("1,2"));
  }

  @Test
  public void addFiveNumbers() {
    assertEquals(15, calc.add("1,2,3,4,5"));
  }

  @Test
  public void addNumbersSplitByNewlinesAndCommas() {
    assertEquals(15, calc.add("1,2\n3,4\n5"));
  }

  @Test
  public void addEmptyStringsSplitByNewlinesAndCommas() {
    assertEquals(0, calc.add(",\n,\n"));
  }

  @Test
  public void setDelimiter() {
    assertEquals(15, calc.add("//[:|]\n1:2|3:4|5"));
  }

  @Test
  public void setDelimiterWithEmptyNumberList() {
    assertEquals(0, calc.add("//[:|]"));
  }

  @Test
  public void throwIfNegativeNumberPresent() {
    try {
      calc.add("-1");
      fail("Expected a NumberFormatException for a negative value");
    } catch (NumberFormatException error) {
      assertEquals("Negatives not allowed: -1", error.getMessage());
    }
  }
}
