package com.redbus.Operator.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="bus_operators")
public class BusOperator {
    @Id
    @Column(name="bus_id")
    private String busId;
    @Column(name="bus_number")
    private String busNumber;
    @Column(name="bus_operator_company_name")
    private String busOperatorCompanyName;
    @Column(name="driver_name")
    private String driverName;
    @Column(name="support_staff")
    private String supportStaff;
    @Column(name="number_seats")
    private int numberSeats;
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
    @Column(name="bus_type")
    private String busType;
    @Column(name="amenities")
    private String amenities;


    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id")
    private TicketCost ticketCost;





}

