package nl.quintor.aegon.calculator.rest.dto;

import lombok.Data;

@Data
public class CalculationDto {
    private int first;
    private int second;
    private String type;
    private double result;
}
