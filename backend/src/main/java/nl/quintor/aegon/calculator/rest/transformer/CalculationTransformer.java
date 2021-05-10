package nl.quintor.aegon.calculator.rest.transformer;

import nl.quintor.aegon.calculator.model.Calculation;
import nl.quintor.aegon.calculator.rest.dto.CalculationDto;

public class CalculationTransformer {
    public static CalculationDto toDto(Calculation calculation) {
        CalculationDto dto = new CalculationDto();
        dto.setFirst(calculation.getFirst());
        dto.setSecond(calculation.getSecond());
        dto.setType(calculation.getType().toString());
        dto.setResult(calculation.getResult());
        return dto;
    }
}
