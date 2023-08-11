package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.from.airport LIKE :from%" +
            "AND f.to.airport LIKE :to%" +
            "AND CAST(f.departureTime AS STRING) LIKE :departureDate")
    List<Flight> searchFlights(String from, String to, String departureDate);
}
