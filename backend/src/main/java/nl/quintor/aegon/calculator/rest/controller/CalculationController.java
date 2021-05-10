package nl.quintor.aegon.calculator.rest.controller;

import nl.quintor.aegon.calculator.model.Calculation;
import nl.quintor.aegon.calculator.rest.dto.CalculationDto;
import nl.quintor.aegon.calculator.rest.dto.CalculationPayload;
import nl.quintor.aegon.calculator.rest.transformer.CalculationTransformer;
import nl.quintor.aegon.calculator.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calculate")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculationController {

    @Autowired
    private CalculationService service;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationDto> add(@RequestBody @Valid CalculationPayload calculationPayload) {
        var calculation = service.add(calculationPayload.getFirst(), calculationPayload.getSecond());
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }

    @PostMapping(value = "/subtract", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationDto> subtract(@RequestBody @Valid CalculationPayload calculationPayload) {
        var calculation = service.subtract(calculationPayload.getFirst(), calculationPayload.getSecond());
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }

    @PostMapping(value = "/multiply", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationDto> multiply(@RequestBody @Valid CalculationPayload calculationPayload) {
        var calculation = service.multiply(calculationPayload.getFirst(), calculationPayload.getSecond());
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }

    @PostMapping(value = "/divide", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationDto> divide(@RequestBody @Valid CalculationPayload calculationPayload) {
        if (calculationPayload.getSecond() == 0) return ResponseEntity.badRequest().build();
        var calculation = service.divide(calculationPayload.getFirst(), calculationPayload.getSecond());
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CalculationDto>> getCalculations() {
        Collection<Calculation> calculations = service.getCalculations();
        if (calculations.isEmpty()) return ResponseEntity.noContent().build();
        List<CalculationDto> dtos = calculations.stream().map(CalculationTransformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
