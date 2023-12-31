package com.redbus.Operator.repository;

import com.redbus.Operator.entity.BusOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusOperatorRepository extends JpaRepository<BusOperator, String> {
    BusOperator findByBusOperatorCompanyName(String busOperatorCompanyName);
    @Query("SELECT bo FROM BusOperator bo WHERE bo.departureCity = :departureCity " +
            "AND bo.arrivalCity = :arrivalCity AND bo.departureDate = :departureDate")
    List<BusOperator> findByDepartureCityAndArrivalCityAndDepartureDate(
            @Param("departureCity") String departureCity,
            @Param("arrivalCity") String arrivalCity,
            @Param("departureDate") LocalDate departureDate);
}
