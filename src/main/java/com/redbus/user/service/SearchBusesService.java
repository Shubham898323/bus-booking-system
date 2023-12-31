package com.redbus.user.service;

import com.redbus.Operator.payload.BusOperatorDto;

import java.time.LocalDate;
import java.util.List;

public interface SearchBusesService {
    List<BusOperatorDto> searchByDepartureCityArrivalCityDepartureDate(String departureCity, String arrivalCity, LocalDate departureDate);
}
