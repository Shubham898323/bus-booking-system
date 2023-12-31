package com.redbus.Operator.service;

import com.redbus.Operator.payload.BusOperatorDto;
import com.redbus.Operator.payload.BusOperatorDto1;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BusOperatorService {
    BusOperatorDto1 scheduleBus(@RequestBody BusOperatorDto1 busOperatorDto1);

    List<BusOperatorDto> getAllBusRecord();


    BusOperatorDto getRecordByCompanyName(String busOperatorCompanyName);
}
