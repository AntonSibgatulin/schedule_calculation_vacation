package com.example.schedule.model.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class HolidayImpl implements IHoliday {

    private List<LocalDate> holidays = null;

    public HolidayImpl() {
        int currentYear = LocalDate.now().getYear();
        this.holidays = initializeHolidays(currentYear);

    }


    @Override
    public List<LocalDate> initializeHolidays(int year) {
        List<LocalDate> holidays = new ArrayList<>();
        // Add New Year's Day
        holidays.add(LocalDate.of(year, 1, 1));

        // Add Independence Day (assuming it's on July 4th)
        holidays.add(LocalDate.of(year, 7, 4));

        // Add Labor Day (first Monday in September)
        LocalDate laborDay = Year.of(year).atMonth(9).atDay(1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        holidays.add(laborDay);

        // Add Thanksgiving (fourth Thursday in November)
        LocalDate thanksgiving = Year.of(year).atMonth(11).atDay(25).with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
        holidays.add(thanksgiving);


        // Add Christmas Day
        holidays.add(LocalDate.of(year, 12, 25));

        // Add all weekends of the year
        List<LocalDate> weekends = getAllWeekendsInYear(year);
        holidays.addAll(weekends);

        return holidays;
    }


    @Override
    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    @Override
    public boolean isWorkingDay(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    @Override
    public List<LocalDate> getHolidays(){
        return this.holidays;
    }


    private List<LocalDate> getAllWeekendsInYear(int year) {

        List<LocalDate> weekends = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(year, 1, 1);

        while (currentDate.getYear() == year) {
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekends.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        return weekends;
    }

}
