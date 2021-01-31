package it.brunasti.hcl.calculator.repository;

import it.brunasti.hcl.calculator.model.OperationRecord;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<OperationRecord, Integer> {
}
