package it.brunasti.hcl.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "operation_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationRecord {

    public OperationRecord(OperationType operationType, double a, double b, double result) {
        super();
        this.operationType = operationType.getCode();
        paramA = a;
        paramB = b;
        this.result = result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //    OPERATION_TYPE VARCHAR(10),
    @Column(name="OPERATION_TYPE")
    private String operationType;

    //    PARAM_A DOUBLE,
    @Column(name="PARAM_A")
    private double paramA;

    //    PARAM_B DOUBLE,
    @Column(name="PARAM_B")
    private double paramB;

    //    OPERATION_RESULT DOUBLE
    @Column(name="OPERATION_RESULT")
    private double result;

    //    OPERATION_RESULT DOUBLE
    @Column(name="OPERATION_ERROR")
    private String error;

}
