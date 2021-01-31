package it.brunasti.hcl.calculator.logic;

import it.brunasti.hcl.calculator.model.OperationRecord;
import it.brunasti.hcl.calculator.model.OperationType;
import it.brunasti.hcl.calculator.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CalculatorWithMemory {

    private final SimpleCalculator simpleCalculator;
    private final ResultRepository repository;


    /**
     * add two integers
     * @param a first addendum
     * @param b second addendum
     * @return result
     */
    public double add(int a, int b) {
        double result = simpleCalculator.add(a,b);
        OperationRecord operationRecord = new OperationRecord(OperationType.ADD,a,b,result);
        repository.save(operationRecord);
        return result;
    }

    /**
     * subtract the second parameter from the first
     * @param a minuend parameter
     * @param b subtrahend parameter
     * @return result
     */
    public double subtract(int a, int b) {
        double result = simpleCalculator.subtract(a,b);
        OperationRecord operationRecord = new OperationRecord(OperationType.SUB,a,b,result);
        repository.save(operationRecord);
        return result;

    }

    /**
     * multiply two integers
     * @param a multiplicand parameter
     * @param b multiplier parameter
     * @return result
     */
    public double multiply(int a, int b) {
        double result = simpleCalculator.multiply(a,b);
        OperationRecord operationRecord = new OperationRecord(OperationType.MULTIPLY,a,b,result);
        repository.save(operationRecord);
        return result;
    }

    /**
     * divide the first parameter by the second
     * @param a dividend parameter
     * @param b divisor parameter
     * @return result
     * @throws ArithmeticException in case of divisor = 0
     */
    public double divide(int a, int b) throws ArithmeticException{
        // TODO Handle exception and store erro in DB
        try {
            double result = simpleCalculator.divide(a, b);
            OperationRecord operationRecord = new OperationRecord(OperationType.DIVIDE, a, b, result);
            repository.save(operationRecord);
            return result;
        } catch (ArithmeticException arithmeticException) {
            log.error("Exception in divide ["+a+"] / ["+b+"]",arithmeticException);
            OperationRecord operationRecord = new OperationRecord(OperationType.DIVIDE, a, b, 0);
            operationRecord.setError(arithmeticException.getMessage());
            repository.save(operationRecord);
            throw arithmeticException;
        }
    }

    /**
     * Retrieve all the history records from the DB
     * @return the list of operation records
     */
    public List<OperationRecord> getHistory() {
        List<OperationRecord> list = new ArrayList<>();
        repository.findAll().forEach(operationRecord -> {list.add(operationRecord);});
        return list;
    }

}
