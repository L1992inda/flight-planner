package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.FlightRequest;
import io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class FlightService {
    private final FlightRepository repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public synchronized Flight add(FlightRequest request) {
        int id = repository.getFlights().stream().mapToInt(Flight::getId).max().orElse(0);
        Flight flight = new Flight(id + 1,
                request.getFrom(),
                request.getTo(),
                request.getCarrier(),
                arrivalDepartureTime(request.getDepartureTime()),
                arrivalDepartureTime(request.getArrivalTime()));
        if (equalFlight(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else if (sameAirport(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (checkTime(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        } else {
            repository.addFlight(flight);
        }
        return flight;
    }


    private boolean equalFlight(Flight flight) {
        return repository.getFlights().stream()
                .anyMatch(c -> c.getFrom().getAirport().equals(flight.getFrom().getAirport()) &
                        c.getFrom().getCity().equals(flight.getFrom().getCity()) &
                        c.getFrom().getCountry().equals(flight.getFrom().getCountry()) &
                        c.getCarrier().equals(flight.getCarrier()) &
                        c.getArrivalTime().equals(flight.getArrivalTime()) &
                        c.getDepartureTime().equals(flight.getDepartureTime()));
    }

    private boolean sameAirport(Flight flight) {
        return flight.getFrom().getAirport().trim().equalsIgnoreCase(flight.getTo().getAirport().trim()) &
                flight.getFrom().getCity().trim().equalsIgnoreCase(flight.getTo().getCity().trim()) &
                flight.getFrom().getCountry().trim().equalsIgnoreCase(flight.getTo().getCountry().trim());
    }

    public boolean checkTime(Flight flight) {
        return flight.getArrivalTime()
                .isBefore(flight.getDepartureTime()) ||
                flight.getDepartureTime().equals(flight.getArrivalTime());
    }

    private LocalDateTime arrivalDepartureTime(String time) {
        return LocalDateTime.parse(time, formatter);

    }

    public void clear() {
        repository.clearFlightsList();
    }

    public Flight fetch(int id) {
        try {
            return repository.fetchFlight(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public synchronized void delete(int id) {
        repository.deleteById(id);
    }

    public List<Airport> searchAirport(String search) {
        return repository.searchByPhrases(search);
    }
}