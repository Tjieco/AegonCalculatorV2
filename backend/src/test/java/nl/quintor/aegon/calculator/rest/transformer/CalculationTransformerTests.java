package nl.quintor.aegon.calculator.rest.transformer;

import nl.quintor.aegon.calculator.model.Calculation;
import nl.quintor.aegon.calculator.model.CalculationType;
import nl.quintor.aegon.calculator.rest.dto.CalculationDto;
import nl.quintor.aegon.calculator.service.SimpleCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculationTransformerTests {

    @Test
    public void testTransformationCalculationToDTO() {
        Calculation calculation = new Calculation();
        calculation.setFirst(100);
        calculation.setSecond(100);
        calculation.setType(CalculationType.ADD);
        calculation.setResult(200);
        calculation.setId(1234);

        CalculationDto dto = CalculationTransformer.toDto(calculation);

        assertEquals(dto.getFirst(), 100);
        assertEquals(dto.getSecond(), 100);
        assertEquals(dto.getType(), "ADD");
        assertEquals(dto.getResult(), 200);
    }
}
