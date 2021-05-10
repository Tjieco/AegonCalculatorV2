package calculator.service;

import nl.quintor.aegon.calculator.service.SimpleCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SimpleCalculatorTests {

    private SimpleCalculator simpleCalculator;

    @BeforeEach
    public void setUp() {
        simpleCalculator = new SimpleCalculator();
    }

    @Test
    public void testAddTwoNumbers() {
        int first = 1;
        int second = 1;
        assertEquals(2, simpleCalculator.add(first, second));
    }

    @Test
    public void testSubtractTwoNumbers() {
        int first = 1;
        int second = 1;
        assertEquals(0, simpleCalculator.subtract(first, second));
    }

    @Test
    public void testMultiplyTwoNumbers() {
        int first = 2;
        int second = 3;
        assertEquals(6, simpleCalculator.multiply(first, second));
    }

    @Test
    public void testDivideTwoNumbers() {
        int first = 4;
        int second = 2;
        assertEquals(2, simpleCalculator.divide(first, second));
    }

    @Test
    public void testDivideZeroByZero() {
        int first = 0;
        int second = 0;
        assertThrows(ArithmeticException.class, () -> simpleCalculator.divide(first, second));
    }

    @Test
    public void testDivideNumberByZero() {
        int first = 3;
        int second = 0;
        assertThrows(ArithmeticException.class, () -> simpleCalculator.divide(first, second));
    }
}
