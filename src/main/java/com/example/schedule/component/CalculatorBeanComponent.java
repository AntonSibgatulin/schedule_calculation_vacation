package com.example.schedule.component;

import com.example.schedule.model.calculator.date.CalculatorDayImpl;
import com.example.schedule.model.calculator.day.CalculatorImpl;
import com.example.schedule.model.calculator.ICalculator;
import com.example.schedule.model.holiday.IHoliday;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CalculatorBeanComponent {


    private final IHoliday iHoliday;

    @Bean
    public ICalculator iCalculator() {
        return new CalculatorImpl(iHoliday);
    }
    @Bean
    public ICalculator iCalculatorDay() {
        return new CalculatorDayImpl(iHoliday);
    }
}
