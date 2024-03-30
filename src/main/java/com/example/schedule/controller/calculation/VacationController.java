package com.example.schedule.controller.calculation;

import com.example.schedule.controller.calculation.service.VacationService;
import com.example.schedule.controller.calculation.dto.Vacation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class VacationController {

    private final VacationService vacationService;

    @Operation(summary = "Получить отпускные за опредленный интервал дней")
    @GetMapping("/calculate/date")
    public ResponseEntity<Vacation> calculateVacationAllowance(
            @RequestParam(value = "averageSalary", defaultValue = "1000000") Double averageSalary,
            @RequestParam(value = "vacationDates", required = false) String vacationDatesParam) {



        return ResponseEntity.ok(vacationService.calculateVacationAllowanceByDate(averageSalary,vacationDatesParam));

    }

    @Operation(summary = "Получить отпускные за опредленное кол-во дней")
    @GetMapping("/calculate")
    public ResponseEntity<Vacation> calculateVacationAllowance(
            @RequestParam(value = "averageSalary", defaultValue = "1000000") Double averageSalary,
            @RequestParam(value = "numberOfDays", required = false) Integer numberOfDays) {


        return ResponseEntity.ok(vacationService.calculateVacationAllowance(averageSalary,numberOfDays));

    }

}