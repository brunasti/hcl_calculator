package it.brunasti.hcl.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OperationType {

    ADD ("plus"),
    SUB ("minus"),
    MULTIPLY ("times"),
    DIVISION ("obelus");

    @Getter
    private final String code;

}
