package io.codelex.flightplanner.service;

import io.codelex.flightplanner.BooleanMethods;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.repository.FlightInMemoryRepository;
import io.codelex.flightplanner.requests.FlightRequest;
import io.codelex.flightplanner.requests.SearchFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


public class FlightInMemoryService extends BooleanMethods implements FlightService {
    private final FlightInMemoryRepository repository;


    public FlightInMemoryService(FlightInMemoryRepository repository) {
        this.repository = repository;
    }

    public synchronized Flight add(FlightRequest request) {
        long id = repository.getFlights().stream().mapToLong(Flight::getId).max().orElse(0);
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

    public boolean equalFlight(Flight flight) {
        return repository.getFlights().stream()
                .anyMatch(c -> c.getFrom().getAirport().equals(flight.getFrom().getAirport()) &
                        c.getFrom().getCity().equals(flight.getFrom().getCity()) &
                        c.getFrom().getCountry().equals(flight.getFrom().getCountry()) &
                        c.getCarrier().equals(flight.getCarrier()) &
                        c.getArrivalTime().equals(flight.getArrivalTime()) &
                        c.getDepartureTime().equals(flight.getDepartureTime()));
    }

    public void clear() {
        repository.clearFlightsList();
    }

    public Flight fetch(long id) {
        try {
            return repository.fetchFlight(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public synchronized void delete(long id) {
        repository.deleteById(id);
    }

    public List<Airport> searchAirport(String search) {

        return repository.searchByPhrases(search);
    }


    public PageResult<Flight> search(SearchFlightRequest searchFlightRequest) {
        if (searchFlightRequest.getTo().equalsIgnoreCase(searchFlightRequest.getFrom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
        return repository.searchFlights(searchFlightRequest);
    }
}

