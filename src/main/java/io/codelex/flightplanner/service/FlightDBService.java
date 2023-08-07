package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.requests.FlightRequest;
import io.codelex.flightplanner.requests.SearchFlightRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FlightDBService implements FlightService {

    private final FlightRepository flightRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    Logger logger = LoggerFactory.getLogger(FlightDBService.class);

    public FlightDBService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public synchronized Flight add(FlightRequest request) {

        Flight newFlight = new Flight();
        newFlight.setFrom(request.getFrom());
        newFlight.setTo(request.getTo());
        newFlight.setCarrier(request.getCarrier());
        newFlight.setDepartureTime(arrivalDepartureTime(request.getDepartureTime()));
        newFlight.setArrivalTime(arrivalDepartureTime(request.getArrivalTime()));

        if (equalFlight(newFlight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else if (newFlight.getFrom().equals(newFlight.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (newFlight.getDepartureTime().isAfter(newFlight.getArrivalTime()) ||
                newFlight.getDepartureTime().equals(newFlight.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            flightRepository.save(newFlight);
        }

        return newFlight;
    }

    public boolean equalFlight(Flight flight) {
        return flightRepository.findAll().stream()
                .anyMatch(c -> c.getFrom().getAirport().equals(flight.getFrom().getAirport()) &
                        c.getFrom().getCity().equals(flight.getFrom().getCity()) &
                        c.getFrom().getCountry().equals(flight.getFrom().getCountry()) &
                        c.getCarrier().equals(flight.getCarrier()) &
                        c.getArrivalTime().equals(flight.getArrivalTime()) &
                        c.getDepartureTime().equals(flight.getDepartureTime()));
    }

    @Override
    public void clear() {
        flightRepository.deleteAll();

    }

    @Override
    public Flight fetch(long id) {
        Optional<Flight> fetch = flightRepository.findById(id);
        if (fetch.isPresent()) {
            return fetch.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void delete(long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Airport> searchAirport(String search) {
        return flightRepository.searchAirport(search);
    }

    @Override
    public Flight findFlightById(long id) {
        Optional<Flight> fetch = flightRepository.findById(id);
        if (fetch.isPresent()) {
            return fetch.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public PageResult<Flight> search(SearchFlightRequest searchFlightRequest) {
        List<Flight> flights = flightRepository.findAll().stream()
                .filter(c -> c.getFrom().getAirport().equalsIgnoreCase(searchFlightRequest.getFrom()) &
                        c.getTo().getAirport().equalsIgnoreCase(searchFlightRequest.getTo()) &&
                        c.getDepartureTime().toString().contains((searchFlightRequest.getDepartureDate()))).toList();
        return new PageResult<>(0, flights.size(), flights);
    }

    @Override
    public LocalDateTime arrivalDepartureTime(String time) {
        return LocalDateTime.parse(time, formatter);
    }
}
