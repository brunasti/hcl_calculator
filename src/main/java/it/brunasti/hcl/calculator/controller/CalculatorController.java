package it.brunasti.hcl.calculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("calculator")
public class CalculatorController {

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void add() {
        log.info("add");
    }


}
