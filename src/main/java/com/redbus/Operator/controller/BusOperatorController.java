package com.redbus.Operator.controller;


import com.redbus.Operator.payload.BusOperatorDto;
import com.redbus.Operator.payload.BusOperatorDto1;
import com.redbus.Operator.service.BusOperatorService;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
@RequestMapping("/api/bus_operator")
public class BusOperatorController {

    private BusOperatorService busOperatorService;

    public BusOperatorController(BusOperatorService busOperatorService) {
        this.busOperatorService = busOperatorService;
    }

    //http://localhost:8080/api/bus_operator

    @PostMapping()
    public ResponseEntity<?> scheduleBus(@Valid @RequestBody BusOperatorDto1 busOperatorDto1, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        BusOperatorDto1 dto = busOperatorService.scheduleBus(busOperatorDto1);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    //Get All BusOperatorRecords
    //http://localhost:8080/api/bus_operator
    @GetMapping
    public ResponseEntity<List<BusOperatorDto>> getAllBusRecord(){
        List<BusOperatorDto> busOperators=busOperatorService.getAllBusRecord();
        return new ResponseEntity<>(busOperators,HttpStatus.OK);

    }

    //Get All BusOperatorRecords based on Operator_company name
    //http://localhost:8080/api/bus_operator/bus_operator_company_name

    @GetMapping("/{busOperatorCompanyName}")
    public ResponseEntity<BusOperatorDto> getRecordByCompanyName(@PathVariable String busOperatorCompanyName) {
        BusOperatorDto busOperatorDto= busOperatorService.getRecordByCompanyName(busOperatorCompanyName);
        return new ResponseEntity<>(busOperatorDto, HttpStatus.OK);



}}
