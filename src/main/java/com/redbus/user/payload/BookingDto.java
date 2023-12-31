package com.redbus.user.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private String bookingId;
    private String busId;
    private String ticketId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private double cost;
    private String busNumber;
    private String busOperatorCompanyName;
    private String departureCity;
    private String arrivalCity;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private double totalTravelTime;
    private LocalDateTime bookingTime;
    private  String message;



}
