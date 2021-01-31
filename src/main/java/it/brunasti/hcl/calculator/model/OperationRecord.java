package it.brunasti.hcl.calculator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "operation_record")
@Data
public class OperationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //    PARAM_A DOUBLE,
    @Column(name="PARAM_A")
    private double paramA;

    //    PARAM_B DOUBLE,
    @Column(name="PARAM_B")
    private double paramB;

    //    OPERATION_TYPE VARCHAR(10),
    @Column(name="OPERATION_TYPE")
    private String operationType;

    //    OPERATION_RESULT DOUBLE
    @Column(name="OPERATION_RESULT")
    private double result;

}
