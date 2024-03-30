package com.example.schedule.model.calculator;

import com.example.schedule.controller.calculation.dto.Vacation;

public interface ICalculator {
    Double calculateVacationAllowance(Vacation vacation);
    Integer numberOfDays(Vacation vacation);
}
