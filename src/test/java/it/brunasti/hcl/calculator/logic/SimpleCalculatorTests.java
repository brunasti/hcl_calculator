package it.brunasti.hcl.calculator.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCalculatorTests {

    @Test
    public void mapper_dumb() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        assertEquals(0, simpleCalculator.add(0,0));
    }


}
