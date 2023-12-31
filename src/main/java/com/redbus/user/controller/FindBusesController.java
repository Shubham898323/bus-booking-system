package com.redbus.user.controller;

import com.redbus.Operator.payload.BusOperatorDto;
import com.redbus.user.service.SearchBusesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class FindBusesController {

    private SearchBusesService searchBusesService;
    public FindBusesController(SearchBusesService searchBusesService) {
        this.searchBusesService = searchBusesService;
    }


    // http://localhost:8080/api/user/searchBuses?departureCity=CityA&arrivalCity=CityB&departureDate=01-01-2023

    @GetMapping("/searchBuses")
    public ResponseEntity<List<BusOperatorDto>> searchBusOperators(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate departureDate) {

        List<BusOperatorDto> result = searchBusesService.searchByDepartureCityArrivalCityDepartureDate(
                departureCity, arrivalCity, departureDate);

        // Convert to BusOperatorResponseDto
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
