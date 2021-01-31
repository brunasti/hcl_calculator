package it.brunasti.hcl.calculator.logic;

import lombok.Data;

@Data
public class SimpleCalculator {

    public double add(int a, int b) {
        return a + b;
    }

    public double subtract(int a, int b) {
        return a - b;
    }

    public double multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        return a / b;
    }


}
