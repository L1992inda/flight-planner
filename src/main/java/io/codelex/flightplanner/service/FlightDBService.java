package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.requests.FlightRequest;
import io.codelex.flightplanner.requests.SearchFlightRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FlightDBService implements FlightService {

    private final FlightRepository flightRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Logger logger = LoggerFactory.getLogger(FlightDBService.class);

    public FlightDBService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight add(FlightRequest request) {


        Flight newFlight = new Flight();
        newFlight.setFrom(request.getFrom());
        newFlight.setTo(request.getFrom());
        newFlight.setCarrier(request.getCarrier());
        newFlight.setDepartureTime(arrivalDepartureTime(request.getDepartureTime()));
        newFlight.setArrivalTime(arrivalDepartureTime(request.getArrivalTime()));

        flightRepository.save(newFlight);

        return newFlight;
    }

    @Override
    public void clear() {

    }

    @Override
    public Flight fetch(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Airport> searchAirport(String search) {
        return null;
    }

    @Override
    public Flight findFlightById(long id) {
        return null;
    }

    @Override
    public PageResult<Flight> search(SearchFlightRequest searchFlightRequest) {
        return null;
    }

    @Override
    public LocalDateTime arrivalDepartureTime(String time) {
        return LocalDateTime.parse(time, formatter);
    }
}
