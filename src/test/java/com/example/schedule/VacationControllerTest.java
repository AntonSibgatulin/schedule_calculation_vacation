package com.example.schedule;


import com.example.schedule.controller.calculation.VacationController;


import com.example.schedule.controller.calculation.service.VacationService;

import com.example.schedule.controller.calculation.dto.Vacation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)

@WebAppConfiguration

public class VacationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VacationService vacationService;

    @InjectMocks
    private VacationController vacationController;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vacationController).build();
    }

    @Test

    public void testCalculateVacationAllowanceByDate() throws Exception {

        Double averageSalary = 1000000.0;
        String vacationDatesParam = "2023-01-01,2023-01-10";
        Vacation expectedVacation = new Vacation(); // create an instance of Vacation with the desired values for testing
        when(vacationService.calculateVacationAllowanceByDate(any(Double.class), any(String.class))).thenReturn(expectedVacation);
        mockMvc.perform(get("/api/v1/calculate/date")
                        .param("averageSalary", String.valueOf(averageSalary))
                        .param("vacationDates", vacationDatesParam))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Vacation responseVacation = JsonConverter.convertTo(result.getResponse().getContentAsString(), Vacation.class);
                    assertEquals(expectedVacation, responseVacation);
                });

    }

    public static class JsonConverter {
        public static <T> T convertTo(String json, Class<T> clazz) {
            try {
                return new ObjectMapper().readValue(json, clazz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert JSON to Java object", e);
            }
        }
    }
}