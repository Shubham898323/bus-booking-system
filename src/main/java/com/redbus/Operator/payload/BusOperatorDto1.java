package com.redbus.Operator.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redbus.Operator.entity.TicketCost;
import com.redbus.Operator.util.CustomLocalDateSerializer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusOperatorDto1 {

    private String busId;

    @NotBlank(message = "Bus number is required")
    @Pattern(regexp = "[A-Z]{2}\\d{4}", message = "Invalid bus number format")
    @Size(max = 255, message = "Maximum length for bus number is 255 characters")
    private String busNumber;

    @NotBlank(message = "Bus operator company name is required")
    @Size(max = 255, message = "Maximum length for bus operator company name is 255 characters")
    private String busOperatorCompanyName;

    @NotBlank(message = "Driver name is required")
    @Size(max = 255, message = "Maximum length for driver name is 255 characters")
    private String driverName;

    @NotBlank(message = "Support staff is required")
    @Size(max = 255, message = "Maximum length for support staff is 255 characters")
    private String supportStaff;

    @NotNull(message = "Number of seats is required")
    @Min(value = 1, message = "Number of seats must be at least 1")
    private Integer numberSeats;

    @NotBlank(message = "Departure city is required")
    @Size(max = 255, message = "Maximum length for departure city is 255 characters")
    private String departureCity;

    @NotBlank(message = "Arrival city is required")
    @Size(max = 255, message = "Maximum length for arrival city is 255 characters")
    private String arrivalCity;

    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;


    @NotNull(message = "Departure date is required")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate departureDate;

    @NotNull(message = "Arrival date is required")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate arrivalDate;

    @NotNull(message = "Total travel time is required")
    @Min(value = 0, message = "Total travel time must be a positive value")
    private double totalTravelTime;

    @NotBlank(message = "Bus type is required")
    @Pattern(regexp = "Economy|Luxury|Standard|Semi-Sleeper|Sleeper|Deluxe", message = "Valid bus types are Economy or Luxury")
    private String busType;

    @NotBlank(message = "Amenities are required")
    @Size(max = 255, message = "Maximum length for amenities is 255 characters")
    private String amenities;

     private TicketCost ticketCost;


}