package io.codelex.flightplanner.service;

import io.codelex.flightplanner.BooleanMethods;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.repository.AirportRepository;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.requests.FlightRequest;
import io.codelex.flightplanner.requests.SearchFlightRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FlightDBService extends BooleanMethods implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    Logger logger = LoggerFactory.getLogger(FlightDBService.class);

    public FlightDBService(FlightRepository flightRepository, AirportRepository airportRepository) {

        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
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
        } else if (sameAirport(newFlight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (checkTime(newFlight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            flightRepository.save(newFlight);


            return newFlight;
        }
    }

    public boolean equalFlight(Flight flight) {
        return flightRepository.findAll().stream().anyMatch(c -> c.getFrom().getAirport().equals(flight.getFrom().getAirport()) &
                c.getFrom().getCity().equals(flight.getFrom().getCity()) &
                c.getFrom().getCountry().equals(flight.getFrom().getCountry()) &
                c.getCarrier().equals(flight.getCarrier()) &
                c.getArrivalTime().equals(flight.getArrivalTime()) &
                c.getDepartureTime().equals(flight.getDepartureTime()));
    }

    @Override
    public Flight fetch(long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @Override
    public synchronized void delete(long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
    }

    @Override
    public void clear() {
        flightRepository.deleteAll();

    }


    @Override
    public List<Airport> searchAirport(String search) {
        return airportRepository.searchAirport(search.toLowerCase().trim());
    }


    @Override
    public PageResult<Flight> search(SearchFlightRequest searchFlightRequest) {
        if (searchFlightRequest.getFrom() != null & searchFlightRequest.getTo() != null &
                searchFlightRequest.getDepartureDate() != null) {

            if (searchFlightRequest.getFrom().equalsIgnoreCase(searchFlightRequest.getTo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            List<Flight> flights = flightRepository.searchFlights(searchFlightRequest.getFrom(), searchFlightRequest.getTo(), searchFlightRequest.getDepartureDate());
            return new PageResult<>(0, flights.size(), flights);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}

