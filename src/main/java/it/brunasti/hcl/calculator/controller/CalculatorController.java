package it.brunasti.hcl.calculator.controller;

import it.brunasti.hcl.calculator.logic.SimpleCalculator;
import it.brunasti.hcl.calculator.rest.response.OperationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("calculator")
public class CalculatorController {

    private final SimpleCalculator simpleCalculator;

    CalculatorController(SimpleCalculator simpleCalculator) {
        this.simpleCalculator = simpleCalculator;
    }

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public OperationResponse add(@RequestParam int a, @RequestParam int b) {
        log.info("add [{}] [{}]", a, b);
        return new OperationResponse(simpleCalculator.add(a,b));
    }

    @GetMapping("/subtract")
    @ResponseStatus(HttpStatus.OK)
    public OperationResponse subtract(@RequestParam int a, @RequestParam int b) {
        log.info("subtract [{}] [{}]", a, b);
        return new OperationResponse(simpleCalculator.subtract(a,b));
    }

    @GetMapping("/multiply")
    @ResponseStatus(HttpStatus.OK)
    public OperationResponse multiply(@RequestParam int a, @RequestParam int b) {
        log.info("multiply [{}] [{}]", a, b);
        return new OperationResponse(simpleCalculator.multiply(a,b));
    }

    @GetMapping("/divide")
    @ResponseStatus(HttpStatus.OK)
    public OperationResponse divide(@RequestParam int a, @RequestParam int b) {
        log.info("divide [{}] [{}]", a, b);
        return new OperationResponse(simpleCalculator.divide(a,b));
    }


}
