package com.redbus.Operator.service;

import com.redbus.Operator.entity.BusOperator;
import com.redbus.Operator.entity.TicketCost;
import com.redbus.Operator.exception.ResourceNotFound;
import com.redbus.Operator.payload.BusOperatorDto;
import com.redbus.Operator.payload.BusOperatorDto1;
import com.redbus.Operator.repository.BusOperatorRepository;
import com.redbus.Operator.repository.TicketCostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BusOperatorServiceImpl implements BusOperatorService {
    private TicketCostRepository  ticketCostRepository;
    private BusOperatorRepository busOperatorRepository;
    private  ModelMapper modelMapper;
    public BusOperatorServiceImpl(TicketCostRepository ticketCostRepository, BusOperatorRepository busOperatorRepository, ModelMapper modelMapper) {
        this.ticketCostRepository = ticketCostRepository;
        this.busOperatorRepository = busOperatorRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public BusOperatorDto1 scheduleBus(BusOperatorDto1 busOperatorDto1) {
        BusOperator busOperator = mapToEntity(busOperatorDto1);
        String busId = UUID.randomUUID().toString();
        busOperator.setBusId(busId);

        // Assuming the TicketCost information is included in the BusOperatorDto
        if (busOperatorDto1.getTicketCost() != null) {
            TicketCost ticketCost = busOperatorDto1.getTicketCost();
            ticketCost.setBusId(busId);  // Set the busId for TicketCost
            ticketCostRepository.save(ticketCost);  // Save the TicketCost separately
            busOperator.setTicketCost(ticketCost);
        }

        BusOperator savedBusOperator = busOperatorRepository.save(busOperator);
        return mapToDto1(savedBusOperator);
    }

    @Override
    public List<BusOperatorDto> getAllBusRecord() {
        List<BusOperator> busList = busOperatorRepository.findAll();
        List<BusOperatorDto> dtos = busList.stream().map(this::mapToDto).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public BusOperatorDto getRecordByCompanyName(String busOperatorCompanyName) {
        BusOperator busOperator = busOperatorRepository.findByBusOperatorCompanyName(busOperatorCompanyName);
        if (busOperator == null) {
            throw new ResourceNotFound("Bus operator not found with company name: " + busOperatorCompanyName);
        }

        return mapToDto(busOperator);
    }

    private BusOperatorDto mapToDto(BusOperator busOperator) {
        return modelMapper.map(busOperator, BusOperatorDto.class);
    }

    private BusOperatorDto1 mapToDto1(BusOperator busOperator) {
        return modelMapper.map(busOperator, BusOperatorDto1.class);
    }

    private BusOperator mapToEntity(BusOperatorDto1 busOperatorDto1) {
        return modelMapper.map(busOperatorDto1, BusOperator.class);
    }
}