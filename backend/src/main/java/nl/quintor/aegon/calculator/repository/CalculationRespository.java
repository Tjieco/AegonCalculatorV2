package nl.quintor.aegon.calculator.repository;

import nl.quintor.aegon.calculator.model.Calculation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalculationRespository extends CrudRepository<Calculation, Long> {
    List<Calculation> findAll();
}
