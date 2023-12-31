package com.redbus.Operator.repository;

import com.redbus.Operator.entity.TicketCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketCostRepository extends JpaRepository<TicketCost,String> {



}
