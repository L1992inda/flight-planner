package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {

    private final List<Flight> flightsList = new ArrayList<>();


    public Flight addFlight(Flight flight) {
        flightsList.add(flight);
        return flight;
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

}


