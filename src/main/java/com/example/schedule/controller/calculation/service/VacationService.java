package com.example.schedule.controller.calculation.service;

import com.example.schedule.controller.calculation.dto.Vacation;
import com.example.schedule.model.calculator.ICalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VacationService {

    @Qualifier("Calculator")
    private final ICalculator iCalculator;

    @Qualifier("CalculatorDay")
    private final ICalculator iCalculatorDay;


    public Vacation calculateVacationAllowanceByDate(Double averageSalary, String vacationDatesParam) {
        List<LocalDate> vacationDates = new ArrayList<>();
        if (vacationDatesParam != null) {
            vacationDates = parseVacationDates(vacationDatesParam);
        }

        Vacation vacation = new Vacation(averageSalary, vacationDates);
        Double vacationAllowance = iCalculator.calculateVacationAllowance(vacation);
        Integer numberOfDay = iCalculator.numberOfDays(vacation);
        return new Vacation(averageSalary, vacationDates, vacationAllowance, numberOfDay);

    }

    public Vacation calculateVacationAllowance(Double averageSalary, Integer numberOfDays) {
        Vacation vacation = new Vacation(averageSalary, numberOfDays);
        Double vacationAllowance = iCalculatorDay.calculateVacationAllowance(vacation);

        return new Vacation(averageSalary, vacationAllowance,numberOfDays);
    }


    private List<LocalDate> parseVacationDates(String vacationDatesParam) {

        String[] dateStrings = vacationDatesParam.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDate> vacationDates = new ArrayList<>();

        for (String dateString : dateStrings) {
            dateString = dateString.trim();
            if (!dateString.isEmpty()) {
                LocalDate vacationDate = LocalDate.parse(dateString, formatter);
                vacationDates.add(vacationDate);
            }
        }

        return vacationDates;
    }
}