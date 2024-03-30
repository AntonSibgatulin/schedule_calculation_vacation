package com.example.schedule.component;

import com.example.schedule.model.holiday.HolidayImpl;
import com.example.schedule.model.holiday.IHoliday;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HolidayBeanComponent {
    @Bean
    public IHoliday iHoliday() {
        return new HolidayImpl();
    }
}
