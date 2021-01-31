package it.brunasti.hcl.calculator.logic;

import it.brunasti.hcl.calculator.exception.CalculatorArithmeticException;
import it.brunasti.hcl.calculator.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorWithMemoryTest {

    SimpleCalculator simpleCalculator;

    @Autowired
    ResultRepository resultRepository;

    @BeforeEach
    public void setup() {
        simpleCalculator = new SimpleCalculator();
        resultRepository.deleteAll();
    }

    @Test
    public void add() {
        CalculatorWithMemory calculator = new CalculatorWithMemory(simpleCalculator, resultRepository);

        assertEquals(0, calculator.add(0,0));
        assertEquals(1, calculator.add(0,1));
        assertEquals(1, calculator.add(1,0));
        assertEquals(2, calculator.add(1,1));
        assertEquals(0, calculator.add(1,-1));

        assertEquals(5, calculator.getHistory().size());
    }

    @Test
    public void subtract() {
        CalculatorWithMemory calculator = new CalculatorWithMemory(simpleCalculator, resultRepository);

        assertEquals(0, calculator.subtract(0,0));
        assertEquals(-1, calculator.subtract(0,1));
        assertEquals(0, calculator.subtract(1,1));

        assertEquals(3, calculator.getHistory().size());
    }

    @Test
    public void multiply() {
        CalculatorWithMemory calculator = new CalculatorWithMemory(simpleCalculator, resultRepository);

        assertEquals(0, calculator.multiply(0,0));
        assertEquals(0, calculator.multiply(0,1));
        assertEquals(0, calculator.multiply(1,0));
        assertEquals(1, calculator.multiply(1,1));
        assertEquals(1, calculator.multiply(-1,-1));
        assertEquals(-1, calculator.multiply(1,-1));
        assertEquals(-1, calculator.multiply(-1,1));

        assertEquals(7, calculator.getHistory().size());
    }

    @Test
    public void divide() {
        CalculatorWithMemory calculator = new CalculatorWithMemory(simpleCalculator, resultRepository);

        assertEquals(0, calculator.divide(0,1));
        assertEquals(1, calculator.divide(1,1));
        assertEquals(1, calculator.divide(-1,-1));
        assertEquals(-1, calculator.divide(1,-1));
        assertEquals(-1, calculator.divide(-1,1));

        assertEquals(5, calculator.getHistory().size());

        assertThrows(CalculatorArithmeticException.class, () -> {
            assertEquals(0, calculator.divide(0,0));
        });

        assertThrows(CalculatorArithmeticException.class, () -> {
            assertEquals(0, calculator.divide(1,0));
        });

        assertEquals(7, calculator.getHistory().size());

    }


}
