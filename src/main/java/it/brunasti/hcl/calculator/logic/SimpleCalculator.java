package it.brunasti.hcl.calculator.logic;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SimpleCalculator {

    /**
     * add two integers
     * @param a first addendum
     * @param b second addendum
     * @return result
     */
    public double add(int a, int b) {
        return a + b;
    }

    /**
     * add two doubles
     * @param a first addendum
     * @param b second addendum
     * @return result
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * subtract the second parameter from the first
     * @param a minuend parameter
     * @param b subtrahend parameter
     * @return result
     */
    public double subtract(int a, int b) {
        return a - b;
    }

    /**
     * multiply two integers
     * @param a multiplicand parameter
     * @param b multiplier parameter
     * @return result
     */
    public double multiply(int a, int b) {
        return a * b;
    }

    /**
     * divide the first parameter by the second
     * @param a dividend parameter
     * @param b divisor parameter
     * @return result
     * @throws ArithmeticException in case of divisor = 0
     */
    public double divide(int a, int b) throws ArithmeticException{
        return a / b;
    }


}
