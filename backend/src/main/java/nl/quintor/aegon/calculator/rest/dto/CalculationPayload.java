package nl.quintor.aegon.calculator.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CalculationPayload {
    @NotNull
    private int first;
    @NotNull
    private int second;
}
