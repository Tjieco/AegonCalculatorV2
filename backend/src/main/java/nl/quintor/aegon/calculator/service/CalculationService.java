package nl.quintor.aegon.calculator.service;

import lombok.extern.slf4j.Slf4j;
import nl.quintor.aegon.calculator.model.Calculation;
import nl.quintor.aegon.calculator.model.CalculationType;
import nl.quintor.aegon.calculator.repository.CalculationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
@Slf4j
public class CalculationService {


    private final SimpleCalculator calculator;
    private final CalculationRespository repository;

    @Autowired
    public CalculationService(SimpleCalculator calculator, CalculationRespository repository) {
        this.calculator = calculator;
        this.repository = repository;
    }

    public Calculation add(int first, int second) {
        Calculation calculation = new Calculation(first, second, CalculationType.ADD);
        calculation.setResult(calculator.add(first, second));
        repository.save(calculation);
        return calculation;
    }

    public Calculation subtract(int first, int second) {
        Calculation calculation = new Calculation(first, second, CalculationType.SUBTRACT);
        calculation.setResult(calculator.subtract(first, second));
        repository.save(calculation);
        return calculation;
    }

    public Calculation multiply(int first, int second) {
        Calculation calculation = new Calculation(first, second, CalculationType.MULTIPLY);
        calculation.setResult(calculator.multiply(first, second));
        repository.save(calculation);
        return calculation;
    }

    public Calculation divide(int first, int second) {
        Calculation calculation = new Calculation(first, second, CalculationType.DIVIDE);
        try {
            double result = calculator.divide(first, second);
            calculation.setResult(result);
        } catch (ArithmeticException e) {
            calculation.setResult(Double.NaN);
        }
        repository.save(calculation);
        return calculation;
    }

    public Collection<Calculation> getCalculations() {
        return repository.findAll();
    }

}
