package nl.quintor.aegon.calculator.service;

import nl.quintor.aegon.calculator.repository.CalculationRespository;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTests {

    @Mock
    private CalculationRespository repository;

    @Mock
    private SimpleCalculator calculator;

    @InjectMocks
    CalculationService calculationService;

    @Test
    public void testAddReturnsAddCalculation() throws Exception {
        when(calculator.add(2, 3)).thenReturn(5.0);
        assertEquals(calculationService.add(2, 3).getResult(), 5.0);
    }

    @Test
    public void testSubtractReturnsSubtractionCalculation() throws Exception {
        when(calculator.subtract(2, 3)).thenReturn(-1.0);
        assertEquals(calculationService.subtract(2, 3).getResult(), -1.0);
    }

    @Test
    public void testMultiplyReturnsMultiplyCalculation() throws Exception {
        when(calculator.add(2, 3)).thenReturn(6.0);
        assertEquals(calculationService.add(2, 3).getResult(), 6.0);
    }

    @Test
    public void testDivideReturnsDivideCalculation() throws Exception {
        when(calculator.add(3, 2)).thenReturn(1.5);
        assertEquals(calculationService.add(3, 2).getResult(), 1.5);
    }

    @Test
    public void testDivideByZeroReturnsNaNCalculation() throws Exception {
        when(calculator.divide(3, 0)).thenThrow(ArithmeticException.class);
        assertEquals(calculationService.divide(3, 0).getResult(), Double.NaN);
    }
}
