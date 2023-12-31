package com.redbus.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name="bookings")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @Column(name = "bus_id")
    private String busId;

    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cost")
    private double cost;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    @Column(name="bus_number")
    private String busNumber;
    @Column(name="bus_operator_company_name")
    private String busOperatorCompanyName;


    @Column(name="departure_city")
    private String departureCity;
    @Column(name="arrival_city")
    private String arrivalCity;
    @Column(name="arrival_time")
    private LocalTime arrivalTime;
    @Column(name="departure_time")
    private LocalTime departureTime;
    @Column(name="departure_date")
    private LocalDate departureDate;
    @Column(name="arrival_date")
    private LocalDate arrivalDate;
    @Column(name="total_travel_time")
    private double totalTravelTime;


}
