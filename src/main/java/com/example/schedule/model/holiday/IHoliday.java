package com.example.schedule.model.holiday;

import java.time.LocalDate;
import java.util.List;

public interface IHoliday {
    List<LocalDate> initializeHolidays(int year);

    boolean isHoliday(LocalDate date);

    boolean isWorkingDay(LocalDate date);

    List<LocalDate> getHolidays();
}
