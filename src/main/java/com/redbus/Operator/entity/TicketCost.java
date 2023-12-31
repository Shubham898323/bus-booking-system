package com.redbus.Operator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ticket_cost")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketCost {

    @Id

    private String ticketId;

    @Column(name="bus_id",nullable = false,unique = true)
    private String busId;


    private double cost;


    private String code;

    @Column(nullable = false)
    private double discountAmount;

//    @OneToOne(mappedBy = "ticketCost")
//    @JoinColumn(name = "bus_id")
//    private BusOperator busOperator;


    @OneToOne(mappedBy = "ticketCost")
    private BusOperator busOperator;


}
