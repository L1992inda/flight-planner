package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("SELECT a FROM Airport a WHERE LOWER( a.airport )LIKE %:search%" +
            "OR  LOWER( a.city) LIKE %:search% " +
            "OR LOWER( a.country) LIKE %:search% ")
    List<Airport> searchAirport(String search);
}
