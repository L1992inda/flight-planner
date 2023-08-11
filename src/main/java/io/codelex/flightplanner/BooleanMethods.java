package io.codelex.flightplanner;

import io.codelex.flightplanner.domain.Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BooleanMethods {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public boolean sameAirport(Flight flight) {
        return flight.getFrom().getAirport().trim().equalsIgnoreCase(flight.getTo().getAirport().trim()) &
                flight.getFrom().getCity().trim().equalsIgnoreCase(flight.getTo().getCity().trim()) &
                flight.getFrom().getCountry().trim().equalsIgnoreCase(flight.getTo().getCountry().trim());

    }

    public boolean checkTime(Flight flight) {
        return flight.getArrivalTime()
                .isBefore(flight.getDepartureTime()) ||
                flight.getDepartureTime().equals(flight.getArrivalTime());

    }

    public LocalDateTime arrivalDepartureTime(String time) {
        return LocalDateTime.parse(time, formatter);
    }
}
