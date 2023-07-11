package io.codelex.flightplanner.response;

import io.codelex.flightplanner.domain.Flight;

import java.util.List;

public class FlightResponse {
    List<Flight> flightList;

    public FlightResponse(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
}

