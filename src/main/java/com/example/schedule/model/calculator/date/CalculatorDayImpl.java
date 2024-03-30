package com.example.schedule.model.calculator.date;

import com.example.schedule.controller.calculation.dto.Vacation;
import com.example.schedule.model.calculator.ICalculator;
import com.example.schedule.model.holiday.IHoliday;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("CalculatorDay")
public class CalculatorDayImpl implements ICalculator {
    private IHoliday iHoliday;

    public CalculatorDayImpl(IHoliday iHoliday) {
        this.iHoliday = iHoliday;
    }

    @Override
    public Double calculateVacationAllowance(Vacation vacation) {
        return vacation.getAverageSalary() / (365 - iHoliday.getHolidays().size()) * vacation.getNumberOfDay();
    }

    @Override
    public Integer numberOfDays(Vacation vacation) {
        return vacation.getNumberOfDay();
    }
}
