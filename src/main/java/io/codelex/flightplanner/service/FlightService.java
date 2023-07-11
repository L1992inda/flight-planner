package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.FlightDto;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.response.FlightResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class FlightService {
    private final FlightRepository repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }
    public synchronized Flight add(FlightDto dto){
        int id  = repository.getFlights().stream().mapToInt(Flight::getId).max().orElse(0);
        Flight flight = new Flight(id+1,
                dto.getFrom(),
                dto.getTo(),
                dto.getCarrier(),
                arrivalDepartureTime(dto.getArrivalTime()),
                arrivalDepartureTime(dto.getDepartureTime()));
    if(equalFlight(flight)){
        throw new ResponseStatusException(HttpStatus.CONFLICT);} else if (sameAirport(flight)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    } else if (strangeDate(flight)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    } else{ repository.addFlight(flight);}
        return flight;}


        private boolean equalFlight(Flight flight){
        return repository.getFlights().stream()
                .anyMatch(c->c.getFromAirport().getAirport().equals(flight.getFromAirport().getAirport())&
                        c.getFromAirport().getCity().equals(flight.getFromAirport().getCity())&
                        c.getFromAirport().getCountry().equals(flight.getFromAirport().getCountry())&
                        c.getCarrier().equals(flight.getCarrier())&
                        c.getArrivalTime().equals(flight.getArrivalTime())&
                        c.getDepartureTime().equals(flight.getDepartureTime()));}

private boolean sameAirport(Flight flight){
        return (flight.getFromAirport().getAirport().trim().equalsIgnoreCase(flight.getToAirport().getAirport().trim())&
                flight.getFromAirport().getCity().trim().equalsIgnoreCase(flight.getToAirport().getCity().trim())&
                flight.getFromAirport().getCountry().trim().equalsIgnoreCase(flight.getToAirport().getCountry().trim()));
}
private boolean strangeDate(Flight flight){
        return (flight.getDepartureTime().isBefore(flight.getArrivalTime())||
                flight.getArrivalTime().equals(flight.getDepartureTime()));
}
    private LocalDateTime arrivalDepartureTime(String time){
        return  LocalDateTime.parse(time, formatter);
    }
    public void clear(){
        repository.clearFlightsList();
    }
    public void deleteById(int id){
        repository.getFlights().removeIf(c->c.getId() == id);
    }
}
