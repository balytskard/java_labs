package org.olexandra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    StringCalculator calculator;

    @Before
    public void setUp() {
        calculator = new StringCalculator();
    }

    // додати до 2 чисел
    @Test
    public void test1() {

        assertEquals(0, calculator.add(""));
        assertEquals(1, calculator.add("1"));
        assertEquals(3, calculator.add("1,2"));
    }

    // від'ємні числа
    @Test(expected = RuntimeException.class)
    public void test5() {
        calculator.add("-1");
    }

}