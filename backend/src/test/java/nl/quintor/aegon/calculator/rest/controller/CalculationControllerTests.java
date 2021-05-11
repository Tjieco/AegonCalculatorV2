package nl.quintor.aegon.calculator.rest.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.quintor.aegon.calculator.model.Calculation;
import nl.quintor.aegon.calculator.model.CalculationType;
import nl.quintor.aegon.calculator.rest.dto.CalculationDto;
import nl.quintor.aegon.calculator.rest.dto.CalculationPayload;
import nl.quintor.aegon.calculator.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CalculationController.class)
public class CalculationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculationService calculationServiceMock;

    final ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();

    @Test
    public void testAdditionCalculation() throws Exception {
        CalculationPayload payload = new CalculationPayload();
        payload.setFirst(1);
        payload.setSecond(1);

        Calculation actual = new Calculation(1, 1, CalculationType.ADD);
        actual.setResult(2);
        given(calculationServiceMock.add(1, 1)).willReturn(actual);

        MockHttpServletResponse response = mockMvc.perform(
                post("/calculate/add")
                        .content(objectMapper.writeValueAsString(payload))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(200));
        CalculationDto calculation = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
        assertThat(calculation.getResult(), is(2.0));
    }

    @Test
    public void testSubtractionCalculation() throws Exception {
        CalculationPayload payload = new CalculationPayload();
        payload.setFirst(2);
        payload.setSecond(1);

        Calculation actual = new Calculation(2, 1, CalculationType.SUBTRACT);
        actual.setResult(1);
        given(calculationServiceMock.subtract(2, 1)).willReturn(actual);

        MockHttpServletResponse response = mockMvc.perform(
                post("/calculate/subtract")
                        .content(objectMapper.writeValueAsString(payload))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(200));
        CalculationDto calculation = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
        assertThat(calculation.getResult(), is(1.0));
    }

    @Test
    public void testMultiplicationCalculation() throws Exception {
        CalculationPayload payload = new CalculationPayload();
        payload.setFirst(2);
        payload.setSecond(3);

        Calculation actual = new Calculation(2, 3, CalculationType.MULTIPLY);
        actual.setResult(6);
        given(calculationServiceMock.multiply(2, 3)).willReturn(actual);

        MockHttpServletResponse response = mockMvc.perform(
                post("/calculate/multiply")
                        .content(objectMapper.writeValueAsString(payload))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(200));
        CalculationDto calculation = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
        assertThat(calculation.getResult(), is(6.0));
    }

    @Test
    public void testDivisionCalculation() throws Exception {
        CalculationPayload payload = new CalculationPayload();
        payload.setFirst(6);
        payload.setSecond(2);

        Calculation actual = new Calculation(6, 2, CalculationType.DIVIDE);
        actual.setResult(3);
        given(calculationServiceMock.divide(6, 2)).willReturn(actual);

        MockHttpServletResponse response = mockMvc.perform(
                post("/calculate/divide")
                        .content(objectMapper.writeValueAsString(payload))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(200));
        CalculationDto calculation = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
        assertThat(calculation.getResult(), is(3.0));
    }

    @Test
    public void testDivisionByZeroReturnsBadRequest() throws Exception {
        CalculationPayload payload = new CalculationPayload();
        payload.setFirst(1);
        payload.setSecond(0);

        MockHttpServletResponse response = mockMvc.perform(
                post("/calculate/divide")
                        .content(objectMapper.writeValueAsString(payload))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void testGetCalculationsNoHistoryReturns204() throws Exception {
        mockMvc.perform(get("/calculate/")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void testGetCalculationsAfterRequestReturnsCorrectHistory() throws Exception {
        Calculation calculation = new Calculation(6, 2, CalculationType.DIVIDE);
        calculation.setResult(3);

        List<Calculation> history = new ArrayList<>();
        history.add(calculation);
        given(calculationServiceMock.getCalculations()).willReturn(history);

        MockHttpServletResponse getResponse = mockMvc.perform(
                get("/calculate/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(getResponse.getStatus(), is(200));
        List<CalculationDto> calculationsResponse = objectMapper.readValue(getResponse.getContentAsString(), new TypeReference<List<CalculationDto>>(){});
        assertThat(calculationsResponse.get(0).getFirst(), is(6));
        assertThat(calculationsResponse.get(0).getSecond(), is(2));
        assertThat(calculationsResponse.get(0).getResult(), is(3.0));
        assertThat(calculationsResponse.get(0).getType(), is("DIVIDE"));
    }
}
