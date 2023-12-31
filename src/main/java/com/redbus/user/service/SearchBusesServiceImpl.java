package com.redbus.user.service;

import com.redbus.Operator.entity.BusOperator;

import com.redbus.Operator.payload.BusOperatorDto;
import com.redbus.Operator.repository.BusOperatorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchBusesServiceImpl implements SearchBusesService {


    private ModelMapper modelMapper;
    private BusOperatorRepository busOperatorRepository;

    public SearchBusesServiceImpl(ModelMapper modelMapper, BusOperatorRepository busOperatorRepository) {
        this.modelMapper = modelMapper;
        this.busOperatorRepository = busOperatorRepository;
    }

    @Override
    public List<BusOperatorDto> searchByDepartureCityArrivalCityDepartureDate(String departureCity, String arrivalCity, LocalDate departureDate) {
        List<BusOperator> busSearches = busOperatorRepository.findByDepartureCityAndArrivalCityAndDepartureDate(departureCity, arrivalCity, departureDate);


        return busSearches.stream()
                .map(bus->mapToDto(bus))
                .collect(Collectors.toList());

    }
    BusOperatorDto mapToDto(BusOperator busOperator) {
        BusOperatorDto dto = new BusOperatorDto();
        dto.setBusId(busOperator.getBusId());
        dto.setBusNumber(busOperator.getBusNumber());
        dto.setBusOperatorCompanyName(busOperator.getBusOperatorCompanyName());
        dto.setDriverName(busOperator.getDriverName());
        dto.setSupportStaff(busOperator.getSupportStaff());
        dto.setNumberSeats(busOperator.getNumberSeats());
        dto.setDepartureCity(busOperator.getDepartureCity());
        dto.setArrivalCity(busOperator.getArrivalCity());
        dto.setArrivalTime(busOperator.getArrivalTime());
        dto.setDepartureTime(busOperator.getDepartureTime());
        dto.setDepartureDate(busOperator.getDepartureDate());
        dto.setArrivalDate(busOperator.getArrivalDate());
        dto.setTotalTravelTime(busOperator.getTotalTravelTime());
        dto.setBusType(busOperator.getBusType());
        dto.setAmenities(busOperator.getAmenities());
        return dto;
    }

}
