package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.requests.SearchFlightRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {

    private final List<Flight> flightsList = new ArrayList<>();


    public void addFlight(Flight flight) {
        flightsList.add(flight);

    }


    public void clearFlightsList() {
        flightsList.clear();
    }

    public List<Flight> getFlights() {
        return flightsList;
    }

    public Flight fetchFlight(int id) {

        return getFlights().stream()
                .filter(c -> c.getId() == id)
                .toList()
                .get(0);
    }

    public void deleteById(int id) {
        getFlights().removeIf(c -> c.getId() == id);
    }

    public List<Airport> searchByPhrases(String search) {

        return getFlights().stream().filter(c -> c.getFrom().getAirport().toLowerCase().contains(search.trim().toLowerCase()) ||
                c.getFrom().getCity().toLowerCase().contains(search.trim().toLowerCase()) ||
                c.getFrom().getCountry().toLowerCase().contains(search.trim().toLowerCase())).map(Flight::getFrom).toList();
    }

    public PageResult<Flight> searchFlights(SearchFlightRequest request) {

        List<Flight> flights = getFlights().stream()
                .filter(c -> c.getFrom().getAirport().equalsIgnoreCase(request.getFrom()) &
                        c.getTo().getAirport().equalsIgnoreCase(request.getFrom()) &
                        c.getDepartureTime().toString().equals(request.getDepartureDate())).toList();
        return new PageResult<>(0, flights.size(), flights);

    }

    public Flight findFlightById(int id) {
        return flightsList.stream()
                .filter(c -> c.getId() == id).findAny().orElse(null);
    }


}


