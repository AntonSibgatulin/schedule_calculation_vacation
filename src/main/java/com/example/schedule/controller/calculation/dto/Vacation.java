package com.example.schedule.controller.calculation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {
    private Double averageSalary;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LocalDate> vacationDates;
    private Double vacationAllowance;

    private Integer numberOfDay;

    public Vacation(Double averageSalary, List<LocalDate> vacationDates) {
        this.averageSalary = averageSalary;
        this.vacationDates = vacationDates;
    }


    public Vacation(Double averageSalary, Integer numberOfDay) {
        this.averageSalary = averageSalary;
        this.numberOfDay = numberOfDay;
    }


    public Vacation(Double averageSalary, Double vacationAllowance, Integer numberOfDay) {
        this.averageSalary = averageSalary;
        this.vacationAllowance = vacationAllowance;
        this.numberOfDay = numberOfDay;
    }
}