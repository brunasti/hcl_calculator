package it.brunasti.hcl.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Arithmetic Exception")
public class CalculatorArithmeticException extends ResponseStatusException {
    public CalculatorArithmeticException(String operation) {
        super(HttpStatus.BAD_REQUEST,"Could not execute the operation [" + operation + "]");
    }
}
