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


}
