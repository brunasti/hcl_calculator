package it.brunasti.hcl.calculator.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCalculatorTests {

    @Test
    public void add() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        assertEquals(0, simpleCalculator.add(0,0));
        assertEquals(1, simpleCalculator.add(0,1));
        assertEquals(1, simpleCalculator.add(1,0));
        assertEquals(2, simpleCalculator.add(1,1));
        assertEquals(0, simpleCalculator.add(1,-1));
    }

    @Test
    public void subtract() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        assertEquals(0, simpleCalculator.subtract(0,0));
        assertEquals(-1, simpleCalculator.subtract(0,1));
        assertEquals(0, simpleCalculator.subtract(1,1));
    }

    @Test
    public void multiply() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        assertEquals(0, simpleCalculator.multiply(0,0));
        assertEquals(0, simpleCalculator.multiply(0,1));
        assertEquals(0, simpleCalculator.multiply(1,0));
        assertEquals(1, simpleCalculator.multiply(1,1));
        assertEquals(1, simpleCalculator.multiply(-1,-1));
        assertEquals(-1, simpleCalculator.multiply(1,-1));
        assertEquals(-1, simpleCalculator.multiply(-1,1));
    }

    @Test
    public void divide() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        assertEquals(0, simpleCalculator.divide(0,1));
        assertEquals(1, simpleCalculator.divide(1,1));
        assertEquals(1, simpleCalculator.divide(-1,-1));
        assertEquals(-1, simpleCalculator.divide(1,-1));
        assertEquals(-1, simpleCalculator.divide(-1,1));

        assertThrows(ArithmeticException.class, () -> {
            assertEquals(0, simpleCalculator.divide(0,0));
        });

        assertThrows(ArithmeticException.class, () -> {
            assertEquals(0, simpleCalculator.divide(1,0));
        });

    }


}
