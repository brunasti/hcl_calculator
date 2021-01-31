package it.brunasti.hcl.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OperationType {

    ADD ("add"),
    SUB ("subtract"),
    MULTIPLY ("multiply"),
    DIVIDE ("divide");

    @Getter
    private final String code;

}
