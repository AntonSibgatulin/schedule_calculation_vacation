package com.example.schedule.model.calculator.day;

import com.example.schedule.controller.calculation.dto.Vacation;
import com.example.schedule.model.calculator.ICalculator;
import com.example.schedule.model.holiday.IHoliday;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("Calculator")
public class CalculatorImpl implements ICalculator {

    private IHoliday iHoliday;

    public CalculatorImpl(IHoliday iHoliday) {
        this.iHoliday = iHoliday;
    }

    @Override
    public Double calculateVacationAllowance(Vacation vacation) {
        List<LocalDate> vacationDates = vacation.getVacationDates();
        double averageSalary = vacation.getAverageSalary();

        if (vacationDates.size() != 2) {
            throw new IllegalArgumentException("Vacation dates must have exactly two elements.");
        }


        // Calculate the total days between the two dates
        int totalDays = (int) ChronoUnit.DAYS.between(vacationDates.get(0), vacationDates.get(1)) + 1;


        // Remove holidays and weekends from the total days
        List<LocalDate> workingDays = new ArrayList<>();

        for (int i = 0; i < totalDays; i++) {
            LocalDate date = vacationDates.get(0).plusDays(i);
            if (!iHoliday.isHoliday(date) && iHoliday.isWorkingDay(date)) {
                workingDays.add(date);
            }
        }


        if (workingDays.isEmpty()) {
            return 0.0;
        }

        // Calculate the total days using the working days
        int totalWorkingDays = workingDays.size();

        return averageSalary / (365 - iHoliday.getHolidays().size()) * totalWorkingDays;

    }


    @Override
    public Integer numberOfDays(Vacation vacation) {
        List<LocalDate> vacationDates = vacation.getVacationDates();
        double averageSalary = vacation.getAverageSalary();

        if (vacationDates.size() != 2) {
            throw new IllegalArgumentException("Vacation dates must have exactly two elements.");
        }


        // Calculate the total days between the two dates
        int totalDays = (int) ChronoUnit.DAYS.between(vacationDates.get(0), vacationDates.get(1)) + 1;


        // Remove holidays and weekends from the total days
        List<LocalDate> workingDays = new ArrayList<>();

        for (int i = 0; i < totalDays; i++) {
            LocalDate date = vacationDates.get(0).plusDays(i);
            if (!iHoliday.isHoliday(date) && iHoliday.isWorkingDay(date)) {
                workingDays.add(date);
            }
        }


        return workingDays.size();
    }

}
