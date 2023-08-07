package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.requests.FlightRequest;
import io.codelex.flightplanner.requests.SearchFlightRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    Flight add(FlightRequest request);

    void clear();

    Flight fetch(long id);

    void delete(long id);

    List<Airport> searchAirport(String search);

    Flight findFlightById(long id);

    PageResult<Flight> search(SearchFlightRequest searchFlightRequest);

    LocalDateTime arrivalDepartureTime(String time);


}
